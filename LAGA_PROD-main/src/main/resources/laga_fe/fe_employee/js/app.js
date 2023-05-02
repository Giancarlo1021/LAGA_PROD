console.log("Hello World!");


/*
// turn this into a function
fetch('https://demo-giancarlo.herokuapp.com/api/customers', {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json'
  }
})
.then(response => {
  if (response.ok) {
    // request was successful, handle the response here
    return response.json();
  } else {
    // request failed
    throw new Error(response.statusText);
  }
})
.then(data => {
  console.log(data);

  // access data from the json object
  const name = data[1].fullName;
  const phone = data[1].phoneNumber;
  const email = data[1].email;
  const address = data[1].address;
  const creditcard = data[1].creditCardNumber;

  // html element
  const userName = document.getElementById("user-name");
  const userEmail = document.getElementById("user-email");
  const userPhone = document.getElementById("user-phone");
  const userAdd = document.getElementById("user-address");
  const userCredit = document.getElementById("user-credit");

  const userNameHTML = name;
  const userEmailHTML = phone;
  const userPhoneHTML = email;
  const userAddHTML = address;
  const userCreditHTML = creditcard;

  userName.innerHTML = userNameHTML;
  userEmail.innerHTML = userEmailHTML;
  userPhone.innerHTML = userPhoneHTML;
  userAdd.innerHTML = userAddHTML;
  userCredit.innerHTML = userCreditHTML;

})
.catch(error => {
  console.error(error);
});
*/

/*
// turn this into get function
// you can also extract the "guests" here
fetch('https://demo-giancarlo.herokuapp.com/api/hotel-rooms/', {
  method: 'GET',
  headers: { 'Content-Type': 'application/json' },
})
.then(response => {
  if (response.ok) {
    // request was successful, handle the response here
    return response.json();
  } else {
    // request failed
    throw new Error(response.statusText);
  }
})
.then(data => {
// get html element to display data
const dataElement = document.getElementById('data');

// loop through the data and create html elements for each reservation
let reservation = '';
data.forEach(item => {
  reservation += `
    <tr>
      <th scope="row">${item.id}</th>
      <td>${item.roomNumber}</td>
      <td>${item.roomType}</td>
      <td>$${item.price}</td>
      <td>${item.isAvailable}</td>
    </tr>
    `;

});

  dataElement.innerHTML = reservation;

})
.catch(error => console.error(error));
*/



