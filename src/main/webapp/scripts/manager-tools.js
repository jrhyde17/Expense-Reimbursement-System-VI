function populateRequests() {
    let xhr = new XMLHttpRequest

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status === 200) {
            let reqs = JSON.parse(xhr.response)
            let heading = document.getElementById('requestsHeading')

            if(Object.keys(reqs).length === 0) {

                let p = document.createElement('P')
                p.innerText = 'Your employees have no pending requests.'
                heading.insertAdjacentElement("afterend",p)
            } else {
                let table = document.createElement('table')
                let headrow = document.createElement('tr')
                let th1 = document.createElement('th')
                let th2 = document.createElement('th')
                let th3 = document.createElement('th')
                let th4 = document.createElement('th')
                let th5 = document.createElement('th')
                th1.innerText = 'ID'
                th2.innerText = 'Employee'
                th3.innerText = 'Amount'
                th4.innerText = 'Summary'
                th5.innerText = 'Image'
                headrow.append(th1)
                headrow.append(th2)
                headrow.append(th3)
                headrow.append(th4)
                headrow.append(th5)
                table.append(headrow)

                for(let i = 0; i < reqs.length; i++) {
                    let row = document.createElement('tr')
                    let req = reqs[i];
                    let td1 = document.createElement('td')
                    let td2 = document.createElement('td')
                    let td3 = document.createElement('td')
                    let td4 = document.createElement('td')
                    let td5 = document.createElement('td')
                    let td6 = document.createElement('td')
                    let td7 = document.createElement('td')

                    td1.innerText = req.id
                    td2.innerText = req.employee
                    td3.innerText = req.amount
                    td4.innerText = req.summary
                    
                    if(req.fileContent.buf.length > 0) {
	                    let img = document.createElement('img')
	                    img.src = "data:image/png;base64," + btoa(req.fileContent.buf);
	                    td5.append(img)
                    } else {
                    	td5.append('no image')
                    }

                    let appForm = document.createElement('form')
                    appForm.id = 'ApproveRequest'
                    appForm.action = '../../ProcessRequest'
                    appForm.method = 'POST'
                    let appAction = document.createElement('input')
                    appAction.name = 'action'
                    appAction.type = 'hidden'
                    appAction.value = 'approve'
                    appForm.append(appAction)
                    let appID = document.createElement('input')
                    appID.name = 'id'
                    appID.type = 'hidden'
                    appID.value = req.id
                    appForm.append(appID)
                    let appButton = document.createElement('button')
                    appButton.type = 'submit'
                    appButton.innerText = 'Approve'
                    appForm.append(appButton)
                    td6.append(appForm)

                    let denForm = document.createElement('form')
                    denForm.id = 'DenyRequest'
                    denForm.action = '../../ProcessRequest'
                    denForm.method = 'POST'
                    let denAction = document.createElement('input')
                    denAction.name = 'action'
                    denAction.type = 'hidden'
                    denAction.value = 'deny'
                    denForm.append(denAction)
                    let denID = document.createElement('input')
                    denID.name = 'id'
                    denID.type = 'hidden'
                    denID.value = req.id
                    denForm.append(denID)
                    let denButton = document.createElement('button')
                    denButton.type = 'submit'
                    denButton.innerText = 'Deny'
                    denForm.append(denButton)
                    td7.append(denForm)
                    
                    row.append(td1)
                    row.append(td2)
                    row.append(td3)
                    row.append(td4)
                    row.append(td5)
                    row.append(td6)
                    row.append(td7)

                    table.append(row)
                }

                heading.insertAdjacentElement("afterend",table)


            }




        }
    }
	
    xhr.open('POST','../../GetRequests')
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send('level=manager&status=pending')
}

window.onload = () => {
    populateRequests()
}