class Page {
  constructor() {
    this.url = "/";
  }
  visit() {
    cy.visit(this.url);
  }

  home() {
    return cy.get("#menu-home");
  }

  catalog() {
    return cy.get("#menu-catalog");
  }
}

module.exports = Page;
