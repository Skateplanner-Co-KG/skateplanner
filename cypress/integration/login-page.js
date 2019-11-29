function loginAsMember(param) {
  cy.contains(' User Name : ').click().type('member');
  cy.contains(' Password: ').click().type('pass');
  cy.contains('Sign In').click();
}

function loginAsTrainer(param) {
  cy.contains(' User Name : ').click().type('trainer');
  cy.contains(' Password: ').click().type('pass2');
  cy.contains('Sign In').click();
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

describe('Planner', function () {
  beforeEach(() => {
    //given
    cy.visit('localhost:8080/login');
    loginAsTrainer();
  })

  // it('addNewEvent', function () {
  //   cy.contains('new Event').click().type('Neues Training');
  //   cy.contains('add-Button').click()
  //   cy.contains('Neues Training')
  // });
});

