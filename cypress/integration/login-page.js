// Context gibt Rahmen
context('Actions', () => {
  beforeEach(() => {
    cy.visit('localhost:8080')
    // erst Seite aufrufen
  })

  it('Redirect to login', function() {
    // URL sollte nach redirect gleich login sein
    cy.url().should('eq', 'http://localhost:8080/login')
  })

  it('Failed login', function() {
    cy.contains('Sign In').click()
    cy.url().should('contain', 'login?error')
  })

  // nach nicht integriert
  it('Successfull login', function() {
    cy.contains('Sign In').click()
    cy.url().should('contain', 'login?error')
  })

})
