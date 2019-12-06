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
    cy.get('[data-cy=password]').click().type('pass2');
    cy.get('[data-cy=signIn]').click();
}

describe('Planner', function () {
    beforeEach(() => {
        //given
        cy.visit('localhost:8080/login');
    });

    it('addNewEventAsTrainer', function () {
        loginAsTrainer();
        // cy.contains('new Event').click().type('Neues Training');
        cy.get('[data-cy=addEventBtn]').click();
        cy.url().should('contain', 'add_event');
        cy.get('[data-cy=eventname]').click().type('Testevent am 6.12. 14 Uhr');
        cy.get('[data-cy=addEventBtn2]').click();
        cy.url().should('eq', 'https://localhost:8080/');
        cy.get('[data-cy=event]').should('contain', 'Testevent am 6.12. 14 Uhr');
    });
});