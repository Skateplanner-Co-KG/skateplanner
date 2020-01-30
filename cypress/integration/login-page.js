function loginAsMember(param) {
  cy.get('[data-cy=username]').click().type('member');
  cy.get('[data-cy=password]').click().type('pass');
  cy.get('[data-cy=signIn]').click();
}

function loginAsTrainer(param) {
  cy.get('[data-cy=username]').click().type('trainer');
  cy.get('[data-cy=password').click().type('pass');
  cy.get('[data-cy=signIn').click();
}

describe('Login-Page', function() {
  beforeEach(() => {
    cy.visit('localhost:8080')
  });

  it('Redirect to login', function() {
    cy.url().should('eq', 'http://localhost:8080/login')
  });

  it('Failed login', function() {
    cy.contains('Sign In').click();
    cy.url().should('contain', 'login?error')
  });

  it('Successfull login as member', function() {
    loginAsMember();
    cy.url().should('eq', 'http://localhost:8080/planner')
  });

  it('Successfull login as trainer', function() {
    loginAsTrainer();
    cy.url().should('eq', 'http://localhost:8080/planner')
  })
});

describe('Navigation-Bar', function () {
  beforeEach(() => {
    cy.visit('localhost:8080/login');
    loginAsTrainer();
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

