const performValidate=()=> {
    var isValid = true

    var myForm = document.forms['application']
    const name = myForm.accountName.value
    const number = myForm.accountNumber.value
    const type = myForm.accountType.value
    const chequeType = myForm.chequeType.value
    const date = myForm.applicationDate.value
    const address = myForm.address.value
    const contact = myForm.contact.value
    const email = myForm.email.value
    const sign = myForm.applicantSignature.value

    var nameErr = document.getElementById("nameErr")
    var numberErr = document.getElementById("numberErr")
    var typeErr = document.getElementById("typeErr")
    var chequeTypeErr = document.getElementById("chequeTypeErr")
    var dateErr = document.getElementById("dateErr")
    var addressErr = document.getElementById("addressErr")
    var contactErr = document.getElementById("contactErr")
    var emailErr = document.getElementById("emailErr")
    var signErr = document.getElementById("signErr")

    //name
    try{
        if(!(/[A-Za-z]/).test(name)){
            throw "Requires only alphabets"
        }
    }
    catch(message){
        isValid=false
        nameErr.innerHTML=message
    }

    //number
    try{
        if(!(/[0-9]{12}/).test(number)){
            throw "Requires only numbers and needs 12 digits"
        }
    }
    catch(message){
        isValid=false
        numberErr.innerHTML=message
    }

    //type
    try{
        if(type===''){
            throw "Requires valid account type"
        }
    }
    catch(message){
        isValid=false
        typeErr.innerHTML=message
    }

    //chequeType
    try{
        if(chequeType==='Cheque Book Type' || chequeType===''){
            throw "Requires valid cheque type"
        }
    }
    catch(message){
        isValid=false
        chequeTypeErr.innerHTML=message
    }

    //contact
    try{
        if(!(/[0-9]{10}/).test(number)){
            throw "Requires only numbers and contact is 10 digits"
        }
    }
    catch(message){
        isValid=false
        contactErr.innerHTML=message
    }
    return isValid


}