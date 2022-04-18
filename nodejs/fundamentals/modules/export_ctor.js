module.exports = function(firstName, lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.getFullName = function() {
		return this.firstName + ' ' + this.lastName;
	}
}