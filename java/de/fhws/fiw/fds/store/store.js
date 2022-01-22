import Vue from 'vue'
import Vuex from 'vuex'
import network from '@/network/network';

const parse = require('parse-link-header');

Vue.use(Vuex)

async function getStudentOfStudyTrip(url) {
    const response = await network.getSingleStudent(url);
    const relations = parse(response.headers.link);
    const student = response.data;
    const studentLinkHref = relations['linkStudentToStudyTrip'];
    const studentUnlinkHref = relations['unlinkStudentFromStudyTrip'];
    if (studentLinkHref != undefined) {
        student.linkHref = studentLinkHref.url;
    }
    if (studentUnlinkHref != undefined) {
        student.unlinkHref = studentUnlinkHref.url;
    }
    return student;
}

async function getStudentOfStudyTripAfterRelationUpdate(response) {
    const relations = parse(response.headers.link);
    const studentUrl = relations['getOneStudentOfStudyTrip'];
    return await getStudentOfStudyTrip(studentUrl.url);
}

export const store = new Vuex.Store({
    state: {
        studyTrips: [],
        selectedStudyTrip: {},
        relatedStudents: [],
        editMode: false,
        createUrl: undefined,
        editUrl: undefined,
        deleteUrl: undefined,
        nextUrl: undefined,
        prevUrl: undefined,
        toggleStudentsOfStudyTripUrl: undefined,
        showingLinkedStudents: false,
        clearSearchField: false
    },
    mutations: {
        SET_STUDY_TRIPS(state, {studyTrips, createUrl, nextUrl, prevUrl}) {
            state.studyTrips = studyTrips;
            state.selectedStudyTrip = {};
            state.clearSearchField = false;
            state.createUrl = createUrl;
            state.prevUrl = prevUrl;
            state.nextUrl = nextUrl;
        },
        SET_STUDY_TRIP(state, {studyTrip, editUrl, deleteUrl}) {
            state.selectedStudyTrip = studyTrip;
            state.editUrl = editUrl;
            state.deleteUrl = deleteUrl;
            state.clearSearchField = true;
            state.editMode = false;
        },
        SET_STUDENTS(state, {students, showingAllLinkedStudents, showingAllLinkableStudents}) {
            state.relatedStudents = students;
            if (showingAllLinkedStudents != undefined) {
                state.toggleStudentsOfStudyTripUrl = showingAllLinkedStudents;
                state.showingLinkedStudents = false;
            } else if (showingAllLinkableStudents != undefined) {
                state.toggleStudentsOfStudyTripUrl = showingAllLinkableStudents;
                state.showingLinkedStudents = true;
            }
        },
        REPLACE_STUDENT(state, {oldStudent, newStudent}) {
            const index = state.relatedStudents.findIndex(student => student.id == oldStudent.id);
            state.relatedStudents.splice(index, 1, newStudent);
        },
        SET_EDIT_MODE(state, editMode) {
            state.editMode = editMode;
            if (editMode == false && state.selectedStudyTrip.id == 0) {
                state.selectedStudyTrip = {};
            }
        }
    },
    actions: {
        async getAllStudentStudyTrips(context) {
            const dispatcherResponse = await network.getDispatcherState();
            const allLinks = parse(dispatcherResponse.headers.link);
            const url = allLinks['getAllStudyTrips'].url;
            await context.dispatch('loadPage', url);
        },
        async loadPage(context, url) {
            const getCollectionResponse = await network.getAllStudentOfStudyTripState(url);
            const nextRelations = parse(getCollectionResponse.headers.link);
            context.commit("SET_STUDY_TRIPS", {
                studyTrips: getCollectionResponse.data,
                createUrl: nextRelations['createStudyTrip'],
                nextUrl: nextRelations['next'],
                prevUrl: nextRelations['prev']
            });
        },
        async loadNextPage(context) {
            await context.dispatch('loadPage', this.state.nextUrl.url);
        },
        async loadPrevPage(context) {
            await context.dispatch('loadPage', this.state.prevUrl.url);
        },
        async getSingleStudentStudyTrip(context, url) {
            const response = await network.getSingleStudentStudyTripState(url);
            const nextRelations = parse(response.headers.link);
            context.commit("SET_STUDY_TRIP", {
                studyTrips: response.data,
                editUrl: nextRelations['updateStudyTrip'],
                deleteUrl: nextRelations['deleteStudyTrip'],
            });
            const studentUrl = response.data.students.href;
            await context.dispatch('getAllStudentsOfStudyTrip', studentUrl);
        },
        async getAllStudentsOfStudyTrip(context, url) {
            const studentsResponse = await network.getAllStudentOfStudyTripState(url);
            const studentsRelations = parse(studentsResponse.headers.link);
            const extendedStudents = [];
            for (let student of studentsResponse.data) {
                const extendedStudent = await getStudentOfStudyTrip(student.self.href);
                extendedStudents.push(extendedStudent);
            }
            context.commit('SET_STUDENTS', {
                students: extendedStudents,
                showingAllLinkedStudents: studentsRelations['getAllLinkedStudents'],
                showingAllLinkableStudents: studentsRelations['getAllLinkableStudents']
            });
        },
        async toggleListOfStudents(context) {
            await context.dispatch('getAllStudentsOfStudyTrip', this.state.toggleStudentsOfStudyTripUrl.url);
        },
        async linkStudent(context, student) {
            const response = await network.linkStudent(student.linkHref, student);
            const updatedStudent = await getStudentOfStudyTripAfterRelationUpdate(response);
            context.commit('REPLACE_STUDENT', {oldStudent: student, newStudent: updatedStudent});
        },
        async unlinkStudent(context, student) {
            const response = await network.unlinkStudent(student.unlinkHref);
            const updatedStudent = await getStudentOfStudyTripAfterRelationUpdate(response);
            context.commit('REPLACE_STUDENT', {oldStudent: student, newStudent: updatedStudent});
        },
        async switchToEditMode(context) {
            context.commit('SET_EDIT_MODE', true);
        },
        async switchToDetailMode(context) {
            context.commit('SET_EDIT_MODE', false);
        }
    },
    getters: {
        isCreateAllowed(state) {
            return state.createUrl != undefined;
        },
        isStudyTipEditable(state) {
            return state.editUrl != undefined;
        },
        isNextPageAvailable(state) {
            return state.nextUrl != undefined;
        },
        isPrevPageAvailable(state) {
            return state.prevUrl != undefined;
        }
    }
})
