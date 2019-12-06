function loginAsTrainer(param) {
    cy.get('[data-cy=username]').click().type('trainer');
    cy.get('[data-cy=password]').click().type('pass2');
    cy.get('[data-cy=signIn]').click();
}

describe('Planner', function () {
    beforeEach(() => {
        //given
        cy.visit('localhost:8080/login');
        loginAsTrainer();
    });

    it('addNewEvent', function () {
      // cy.contains('new Event').click().type('Neues Training');
      cy.get('[data-cy=addEventBtn]').click();
      cy.url().should('contains', 'add_event');
    });
});