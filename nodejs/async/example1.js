/**
 * if isMomHappy = true
 * the output is :
 * before asking Mom
 * end of process
 * Hey friend, I have a new black samsung phone
 * after asking mom
 */
const isMomHappy = true;

const willIGetNewPhone = new Promise(executor);

(async () => {
    await askMom();
})();
console.log('end of process');

async function askMom() {
    try {
        console.log('before asking Mom');

        let phone = await willIGetNewPhone;
        let msg = await showOff(phone);

        console.log(msg);
        console.log('after asking mom');
    } catch(error) {
        console.log(error.message);
    }
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
