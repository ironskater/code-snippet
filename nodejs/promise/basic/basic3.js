/**
 * if isMomHappy = true
 * the output is :
 * before asking Mom
 * after asking mom
 * Hey friend, I have a new black samsung phone
 */
const isMomHappy = true;

const willIGetNewPhone = new Promise(executor);

askMom();

function askMom() {
    console.log('before asking Mom');
    willIGetNewPhone
        .then(showOff)
        .then(function(fulfilled) {
            console.log(fulfilled);
        })
        .catch(function(error) {
            console.log(error.message);
        });
    console.log('after asking mom');
}

function executor(resolve, reject) {
    if(isMomHappy) {
        let phone = {
            brand: 'samsung',
            color: 'black'
        };
        resolve(phone);
    } else {
        let reason = new Error('mom is not happy');
        reject(reason);
    }
}

function showOff(phone) {
    return new Promise(
        function(resolve, reject) {
            let msg = 'Hey friend, I have a new ' + phone.color + ' ' + phone.brand + ' phone';

            resolve(msg);
        }
    );

    // also can rewrite as
    // let msg = 'Hey friend, I have a new ' + phone.color + ' ' + phone.brand + ' phone';
    // return Promise.resolve(msg);
}
