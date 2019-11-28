context('Actions', () => {
    beforeEach(() => {
        cy.visit('localhost:8080/login');
        cy.contains(' User Name : ').click().type('trainer');
        cy.contains(' Password: ').click().type('pass2');
        cy.contains('Sign In').click()
    });

    it('navBarElements', function () {
        cy.contains('About Us');
        cy.contains('Contact');
        cy.contains('Sign Out')
    });

    it('redirections', function () {
        cy.contains('About Us').click();
        cy.url().should('contain','about_us');
        cy.contains('Contact').click();
        cy.url().should('contain','contact');
    });

    it('logOut', function () {
        cy.contains('Sign Out').click();
        cy.url().should('contain', 'login')
    })
});