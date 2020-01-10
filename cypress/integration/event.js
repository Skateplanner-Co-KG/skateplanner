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

    it('addNewEventAsTrainer', function () {
        loginAsTrainer();
        cy.get('[data-cy=addEventBtn]').click();
        cy.url().should('contain', 'add_event');
        cy.get('[data-cy=eventname]').click().type('testEvent');
        //cy.get('[data-cy=eventtype]').click().type('testEvent');
        cy.get('[data-cy=timespan').click().type('15-01-2020');
        cy.get('[data-cy=description').click().type('Schuhe mitbringen');
        cy.get('[data-cy=participants').click().type('Laura, Klara');
        cy.get('[data-cy=addEventBtn2]').click();
        cy.url().should('eq', 'https://localhost:8080/planner');
        //cy.get('[data-cy=event]').should('contain', 'Testevent am 6.12. 14 Uhr');
    });
});