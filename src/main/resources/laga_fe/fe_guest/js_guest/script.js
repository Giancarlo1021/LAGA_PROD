

//popover
/*
const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]');
const popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl));

const popover = new bootstrap.Popover('.popover-dismiss', {
  trigger: 'focus'
})*/

// vars
let check_in;
let check_out;
let room_type;
let room_number;
let room_availability;
let room_price;
let num_stay;
let res_total;
let res_id;
let cus_lname;
let cus_fname;
let cus_email;
let cus_phone;

let hotel_room_id;


const initForm = document.querySelector('#init_form');

initForm.addEventListener('submit', e => {
  e.preventDefault();


  
  // get data from init html form
  check_in = document.querySelector('#check_in').value;
  check_out = document.querySelector('#check_out').value;
  room_type = document.querySelector('#room_type').value;

  // for invalid inputs
  const res_button = document.getElementById('makeResButton');
  var roomnum_arr = new Array();

  let checkInParts = check_in.split('-');
  let fday = parseInt(checkInParts[2]);
  let checkOutParts = check_out.split('-');
  let lday = parseInt(checkOutParts[2]);
  num_stay = lday - fday;

  if(isNaN(fday) || isNaN(lday) || isNaN(num_stay) || num_stay < 0){
    console.log('invalid input');
    const availData = document.getElementById('getAvail');
    availData.innerHTML = "Invalid date input. Please select proper dates.";
    res_button.innerHTML = 'Close and input valid dates.';
  }
  else{
        console.log(check_in);
        console.log(check_out);
        console.log(room_type);
        console.log(fday);
        console.log(lday);
        console.log('Duration: ' + num_stay);

        // post request with api to put on DB
        fetch('https://demo-giancarlo.herokuapp.com/api/hotel-rooms/available_rooms', {
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
        const availData = document.getElementById('getAvail');

        // loop through the data and create html elements for each reservation
        let avail_print = '';
        data.forEach(item => {
          //print only for appropriate room type
          roomnum_arr.push(item.roomNumber);
          if(room_type == item.roomType){
          let loc_total = item.price * num_stay;
          avail_print += `
              <tr>
                <td scope="row" id="book_price">$${item.price}</td>
                <td id="book_room">${item.roomType} Room</td>
                <td id="book_nights">${num_stay} Nights</td>
                <td id="book_total">$${loc_total}</td>
                <td>${item.roomNumber}</td>
              </tr>
              `; 
          }
        });

          availData.innerHTML = avail_print;

        })
        .catch(error => console.error(error));



        const reserveNumber = document.querySelector('#book_room');

        

reserveNumber.addEventListener('submit', e => {


  e.preventDefault();

    fetch('https://demo-giancarlo.herokuapp.com/api/hotel-rooms/available_rooms', {
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
  room_number = document.querySelector('#book_room_number').value;
  data.forEach(item => {
    if(room_number == item.roomNumber){
      room_type = item.roomType;
      room_price = item.price;
      res_total = item.price * num_stay;
      hotel_room_id = item.id;
      room_number = item.roomNumber;
    }
  });

  console.log(room_number);
  console.log(room_type);
  console.log(room_price);
  console.log(res_total);

  })
  .catch(error => console.error(error));

});





const subForm = document.querySelector('#submissionForm');



subForm.addEventListener('submit', e => {


    e.preventDefault();

            cus_fname= document.querySelector('#fname').value;
            cus_lname = document.querySelector('#lname').value;
            cus_email = document.querySelector('#g_email').value;
            cus_phone = document.querySelector('#g_phone').value;
            
            console.log(cus_fname);
            console.log(cus_lname);
            console.log(cus_email);
            console.log(cus_phone);

            const reservationData = {
              "customer": {
                  "firstName": cus_fname,
                  "lastName": cus_lname,
                  "email": cus_email,
                  "phoneNumber": cus_phone
              },
              "hotelRoomId": hotel_room_id,
              "checkInDate": check_in, 
              "checkOutDate": check_out,
              "totalAmount": res_total
            };

            console.log(reservationData);

            const f_link = 'https://demo-giancarlo.herokuapp.com/api/transactions/hotels/' + hotel_room_id +'/transactions';
            console.log(f_link);

            fetch(f_link, {
              method: 'POST',
              headers: {
                //'Accept': 'application/json',
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(reservationData)
            })
            .then(response => response.json())
            .then(data => {
              console.log(data);
            })
            .catch(error => {
              console.error(error);
            });


            const conf = document.getElementById('conf_det');
            let conf_print ='';
            conf_print = `
              You are all set for vacation, ${cus_fname} ${cus_lname}!
              <br>
              Here are your confirmation details:
              <ul>
                <li>Check in: ${check_in}</li>
                <li>Check out: ${check_out} </li>
                <li>Room Type: ${room_type} </li>
                <li>Length of Stay: ${num_stay} </li>
                <li>Total Amount: ${res_total} </li>
              </ul>
                See you soon!

                Best,
                LAGA Hotel
            `;



            conf.innerHTML = conf_print;
            
          });
    }
  });
    /*
    e.preventDefault();
      cus_fname= document.querySelector('#fname').value;
      cus_lname = document.querySelector('#lname').value;
      cus_email = document.querySelector('#g_email').value;
      cus_phone = document.querySelector('#g_phone').value;
      
      console.log(cus_fname);
      console.log(cus_lname);
      console.log(cus_email);
      console.log(cus_phone);

      const reservationData = {
        "customer": {
            "firstName": cus_fname,
            "lastName": cus_lname,
            "email": cus_email,
            "phoneNumber": cus_phone
        },
        "hotelRoomId": hotel_room_id,
        "checkInDate": check_in, 
        "checkOutDate": check_out,
        "totalAmount": res_total
      };

      console.log(reservationData);

      const f_link = 'https://demo-giancarlo.herokuapp.com/api/transactions/hotels/' + hotel_room_id +'/transactions';
      console.log(f_link);

      fetch(f_link, {
        method: 'POST',
        headers: {
          //'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(reservationData)
      })
      .then(response => response.json())
      .then(data => {
        console.log(data);
      })
      .catch(error => {
        console.error(error);
      });


      const conf = document.getElementById('conf_det');
      let conf_print ='';
      conf_print = `
        You are all set for vacation, ${cus_fname} ${cus_lname}!
        <br>
        Here are your confirmation details:
        <ul>
          <li>Check in: ${check_in}</li>
          <li>Check out: ${check_out} </li>
          <li>Room Type: ${room_type} </li>
          <li>Length of Stay: ${num_stay} </li>
          <li>Total Amount: ${res_total} </li>
        </ul>
          See you soon!

          Best,
          LAGA Hotel
      `;



      conf.innerHTML = conf_print;

});
      }



});*/


/* 
const reserveNumber = document.querySelector('#book_room');

reserveNumber.addEventListener('submit', e => {
  e.preventDefault();

    fetch('https://demo-giancarlo.herokuapp.com/api/hotel-rooms/available_rooms', {
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
  room_number = document.querySelector('#book_room_number').value;
  data.forEach(item => {
    if(room_number == item.roomNumber){
      room_type = item.roomType;
      room_price = item.price;
      res_total = item.price * num_stay;
      hotel_room_id = item.id;
      room_number = item.roomNumber;
    }
  });

  console.log(room_number);
  console.log(room_type);
  console.log(room_price);
  console.log(res_total);

  })
  .catch(error => console.error(error));

});





const subForm = document.querySelector('#submissionForm');



subForm.addEventListener('submit', e => {
  e.preventDefault();
      cus_fname= document.querySelector('#fname').value;
      cus_lname = document.querySelector('#lname').value;
      cus_email = document.querySelector('#g_email').value;
      cus_phone = document.querySelector('#g_phone').value;
      
      console.log(cus_fname);
      console.log(cus_lname);
      console.log(cus_email);
      console.log(cus_phone);

      const reservationData = {
        "customer": {
            "firstName": cus_fname,
            "lastName": cus_lname,
            "email": cus_email,
            "phoneNumber": cus_phone
        },
        "hotelRoomId": hotel_room_id,
        "checkInDate": check_in, 
        "checkOutDate": check_out,
        "totalAmount": res_total
      };

      console.log(reservationData);

      const f_link = 'https://demo-giancarlo.herokuapp.com/api/transactions/hotels/' + hotel_room_id +'/transactions';
      console.log(f_link);

      fetch(f_link, {
        method: 'POST',
        headers: {
          //'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(reservationData)
      })
      .then(response => response.json())
      .then(data => {
        console.log(data);
      })
      .catch(error => {
        console.error(error);
      });

      const conf = document.getElementById('conf_det');
      let conf_print ='';
      conf_print = `
        You are all set for vacation, ${cus_fname} ${cus_lname}!
        <br>
        Here are your confirmation details:
        <ul>
          <li>Check in: ${check_in}</li>
          <li>Check out: ${check_out} </li>
          <li>Room Type: ${room_type} </li>
          <li>Length of Stay: ${num_stay} </li>
          <li>Total Amount: ${res_total} </li>
        </ul>
          See you soon!

          Best,
          LAGA Hotel
      `;



      conf.innerHTML = conf_print;

});

*/