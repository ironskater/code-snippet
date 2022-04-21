const dotenv = require('dotenv');

dotenv.config(); // 預設為當前目錄下的.env
dotenv.config({path: './params/.env'});

console.log(`the host is: ${process.env.HOST}`);
console.log(`the port is: ${process.env.PORT}`);
