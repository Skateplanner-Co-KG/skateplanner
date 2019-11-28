context('Actions', () => {
    beforeEach(() => {
        cy.visit('localhost:8080/login');
        cy.contains(' User Name : ').click().type('trainer');
        cy.contains(' Password: ').click().type('pass2');
        cy.contains('Sign In').click()
    });

    it('navBarElements', function () {
        cy.contains('Home');
        cy.contains('About Us');
        cy.contains('Planner');
        cy.contains('Contact');
        cy.contains('Sign Out')
    });

    it('redirectionToAboutUs', function () {
        cy.contains('About Us').click();
        cy.url().should('contain','about_us')
    });

    it('logOut', function () {
        cy.contains('Sign Out').click();
        cy.url().should('contain', 'login')
    })

});