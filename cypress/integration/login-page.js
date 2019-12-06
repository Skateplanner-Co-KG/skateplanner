function loginAsMember(param) {
  cy.get('[data-cy=username]').click().type('member');
  cy.get('[data-cy=password]').click().type('pass');
  cy.get('[data-cy=signIn]').click();
}

function loginAsTrainer(param) {
  cy.get('[data-cy=username]').click().type('trainer');
  cy.get('[data-cy=password').click().type('pass2');
  cy.get('[data-cy=signIn').click();
}

describe('Login-Page', function() {
  beforeEach(() => {
    // given
    cy.visit('localhost:8080')
  });

  it('Redirect to login', function() {
    // then
    cy.url().should('eq', 'http://localhost:8080/login')
  });

  it('Failed login', function() {
    // when
    cy.contains('Sign In').click();
    // then
    cy.url().should('contain', 'login?error')
  });

  it('Successfull login as member', function() {
    // when
    loginAsMember();
    // then
    cy.url().should('eq', 'http://localhost:8080/')
  });

  it('Successfull login as trainer', function() {
    // when
    loginAsTrainer();
    // then
    cy.url().should('eq', 'http://localhost:8080/')
  })
});

describe('Navigation-Bar', function () {
  beforeEach(() => {
    // given
    cy.visit('localhost:8080/login');
    loginAsTrainer();
  });

  it('navBarElements', function () {
    // then
    cy.contains('About us');
    cy.contains('Contact');
    cy.contains('Sign Out')
  });

  it('redirections', function () {
    // when
    cy.contains('About us').click();
    // then
    cy.url().should('contain','about_us');
    // when
    cy.contains('Contact').click();
    // then
    cy.url().should('contain','contact');
  });

  it('logOut', function () {
    // when
    cy.contains('Sign Out').click();
    // then
    cy.url().should('contain', 'login')
  })
});

