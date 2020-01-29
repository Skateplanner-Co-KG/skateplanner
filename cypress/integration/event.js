/**
 * Author: Konstantin
 */

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

describe('Planner', function () {
    beforeEach(() => {
        //given
        cy.visit('localhost:8080/login');
    });

    it('addNewEventAsTrainerAndDelete', function () {
        loginAsTrainer();
        cy.get('[data-cy=addEventBtn]').click();
        cy.url().should('contain', 'add_event');
        cy.get('[data-cy=eventname]').click().type('Freitagstraining');
        //cy.get('[data-cy=eventtype]').click().type('testEvent');
        cy.get('[data-cy=timespan]').click().type('24-01-2020');
        cy.get('[data-cy=description]').click().type('Schuhe mitbringen');
        cy.get('[data-cy=addEventBtn2]').click();
        cy.url().should('eq', 'http://localhost:8080/planner');

        cy.get('div#cal').contains('Freitagstraining');

        cy.get('[data-cy=deleteEventBtn]').click();
        cy.url().should('contain', 'delete_event');
        cy.get('[data-cy=deleteEventName]').click().type('Freitagstraining')
        cy.get('[data-cy=deleteEventBtn2').click();

        cy.url().should('eq', 'http://localhost:8080/planner');
    });
});