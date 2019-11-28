// Context gibt Rahmen
context('Actions', () => {
  beforeEach(() => {
    cy.visit('localhost:8080')
    // erst Seite aufrufen
  });

  it('Redirect to login', function() {
    // URL sollte nach redirect gleich login sein
    cy.url().should('eq', 'http://localhost:8080/login')
  });

  it('Failed login', function() {
    cy.contains('Sign In').click();
    cy.url().should('contain', 'login?error')
  });

  it('Successfull login as member', function() {
    cy.contains(' User Name : ').click().type('member');
    cy.contains(' Password: ').click().type('pass');
    cy.contains('Sign In').click();
    cy.url().should('eq', 'http://localhost:8080/')
  });

  it('Successfull login as trainer', function() {
    cy.contains(' User Name : ').click().type('trainer');
    cy.contains(' Password: ').click().type('pass2');
    cy.contains('Sign In').click();
    cy.url().should('eq', 'http://localhost:8080/')
  })

});
