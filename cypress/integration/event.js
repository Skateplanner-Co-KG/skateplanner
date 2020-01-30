function loginAsMember(param) {
    cy.get('[data-cy=username]').click().type('member');
    cy.get('[data-cy=password]').click().type('pass');
    cy.get('[data-cy=signIn]').click();
}

function loginAsTrainer(param) {
    cy.get('[data-cy=username]').click().type('trainer');
    cy.get('[data-cy=password]').click().type('pass');
    cy.get('[data-cy=signIn]').click();
}

function createEvent(param) {
    loginAsTrainer();
    cy.get('[data-cy=addEventBtn]').click();
    cy.url().should('contain', 'add_event');
    cy.get('[data-cy=eventname]').click().type('Freitagstraining');
    cy.get('[data-cy=timespan]').click().type('24-01-2020');
    cy.get('[data-cy=description]').click().type('Schuhe mitbringen');
    cy.get('[data-cy=addEventBtn2]').click();
    cy.url().should('eq', 'http://localhost:8080/planner');
    cy.get('div#cal').contains('Freitagstraining');
}

function deleteEvent(param) {
    cy.get('[data-cy=deleteEventBtn]').click();
    cy.url().should('contain', 'delete_event');
    cy.get('[data-cy=deleteEventName]').click().type('Freitagstraining');
    cy.get('[data-cy=deleteEventBtn2').click();

    cy.url().should('eq', 'http://localhost:8080/planner');
}


describe('Planner', function () {
    beforeEach(() => {
        cy.visit('localhost:8080/login');
    });

    it('addNewEventAsTrainerAndDelete', function () {
        createEvent();
        deleteEvent();
    });

    it('addEventAsTrainerSoMemberCanSeeItThenDelete', function () {
        createEvent();
        cy.contains('Sign Out').click();
        cy.url().should('contain', 'login');
        loginAsMember();
        cy.get('div#cal').contains('Freitagstraining');
        cy.contains('Sign Out').click();
        loginAsTrainer();
        deleteEvent();
    });
});