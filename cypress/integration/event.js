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
        cy.get('[data-cy=eventname]').click().type('Montagstraining');
        //cy.get('[data-cy=eventtype]').click().type('testEvent');
        cy.get('[data-cy=timespan]').click().type('13-01-2020');
        cy.get('[data-cy=description]').click().type('Schuhe mitbringen');
        //cy.get('[data-cy=participants]').click().type('Laura, Klara');
        cy.get('[data-cy=addEventBtn2]').click();
        cy.url().should('eq', 'http://localhost:8080/planner');
        //cy.get('[data-cy=event]').should('contain', 'Testevent am 6.12. 14 Uhr');
        cy.get('[data-cy=deleteEventBtn]').click();
        cy.url().should('contain', 'delete_event');
        //cy.get('[data-cy=deleteEventId]').click().type('')
        // Der muss die ID aus der Tabelle lesen, damit er nach ID löschen kann
    });
});