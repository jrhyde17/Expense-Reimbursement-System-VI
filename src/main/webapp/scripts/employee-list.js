function populateEmployees() {
    let xhr = new XMLHttpRequest

    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 & xhr.status === 200) {
            let employees = JSON.parse(xhr.response)
            let title = document.getElementById('title')

            if(Object.keys(employees).length === 0) {

                let p = document.createElement('P')
                p.innerText = 'There are no employees.'
                heading.insertAdjacentElement("afterend",p)
            } else {
                let table = document.createElement('table')
                let headrow = document.createElement('tr')
                let th1 = document.createElement('th')
                let th2 = document.createElement('th')
                th1.innerText = 'Employee'
                th2.innerText = 'Manager'
                headrow.append(th1)
                headrow.append(th2)
                table.append(headrow)
                for(let i = 0; i < employees.length; i++) {
                    let row = document.createElement('tr')
                    let emp = employees[i]
                    let td1 = document.createElement('td')
                    let td2 = document.createElement('td')
                    
                    td1.innerText = emp.username
                    td2.innerText = emp.manager

                    row.append(td1)
                    row.append(td2)

                    table.append(row)
                }

                title.insertAdjacentElement("afterend",table)

            }
            
        }

    }

    xhr.open('POST','../../ViewEmployees')
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
    xhr.send('single=false')

}

window.onload = () => {
    populateEmployees()
}