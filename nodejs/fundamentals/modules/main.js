// REF: https://www.tutorialsteacher.com/nodejs/nodejs-module-exports
/**
 * Export Literals:
 * You must specify ./ as a path of root folder to import a local module.
 * However, you do not need to specify the path to import Node.js core modules or NPM modules in the require() function.
 */
const msg1 = require('./export_literal.js');

/**
 * Export Object:
 * The exports is an object. So, you can attach properties or methods to it.
 */
const msg2 = require('./export_property');

const func1 = require('./export_function1');
const func2 = require('./export_function2');
const obj1 = require('./export_object')
const ctor1 = require('./export_ctor')

console.log(msg1);
console.log(msg2.SimpleMessage);
func1.log('export function1');
func2('export function2');
console.log(obj1.firstName + ' ' + obj1.lastName);

const person = new ctor1('hari', 'shin');
console.log(person.getFullName());

/**
 * Load Module from the Separate Folder
 *
 * [Method1] Use the full path of a module file where you have exported it using module.exports.
 * For example, if the log module in the log.js is stored under the utility folder under the root folder of your application,
 * then import it, as shown below.
 */
const util1 = require('./utility/arithematic');
console.log(util1.add(2, 3));
console.log(util1.sub(2, 3));

/**
 * [Method2] Use package.json
 * Node.js will search for a package definition file called package.json inside the utility folder.
 * This is because Node assumes that this folder is a package and will try to look for a package definition.
 * The package.json file should be in a module directory. The package.json under utility folder specifies the file name using the main key
 *
 * Node.js will find the arithematic.js file using the main entry in package.json and import it.
 */
const util2 = require('./utility');
console.log(util2.add(5, 6));
console.log(util2.sub(6, 5));
