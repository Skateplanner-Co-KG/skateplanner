describe('Registration', function() {
    it('registration', function () {
        cy.visit('localhost:8080');
        cy.url().should('eq', 'http://localhost:8080/login');
        //cy.contains()
    })
});