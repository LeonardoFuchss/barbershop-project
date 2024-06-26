
document.addEventListener('DOMContentLoaded', function(event) {
    event.preventDefault();
    const form = document.getElementById('useForm');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const name = document.getElementById('name').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            const user = {
                name: name,
                email: email,
                password: password
            };

            fetch('http://localhost:8080/user/save', { 
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na solicitação: ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                alert('Usuário cadastrado com sucesso!');
                window.location.href = "entrar.html";
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Erro ao cadastrar usuário.');
            });
        });
    }
});

let userId;
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('useForm2');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;


            fetch(`http://localhost:8080/user/search?email=${email}&password=${password}`,)

            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na solicitação: ' + response.statusText);
                }
                return response.json();
            })
            .then(body => {
                userId = parseInt(body);
                localStorage.setItem('userId', userId)
                
                window.location.href = 'service.html'
                 // Redireciona para a página de agendamento
            })
            .catch(error => {
                console.error('Erro:', error);
                showCustomAlert('Erro ao fazer o login. Verifique seu cadastro ou sua senha.', 4000);
            });
        });
    }
});

function showCustomAlert(message, displayTime) {
    const modal = document.getElementById('customAlert');
    const alertMessage = document.getElementById('alertMessage');
    alertMessage.textContent = message;

    // Exibe o modal
    modal.style.display = 'block';

    // Após o tempo especificado, oculta o modal
    setTimeout(() => {
        modal.style.display = 'none';
    }, displayTime);
}

let serviceId;
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('useForm4');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault(); // Impede o envio padrão do formulário

            const serviceType = document.getElementById('serviceType').value;
            
            fetch(`http://localhost:8080/service/search?nameService=${encodeURIComponent(serviceType)}`)

            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na solicitação: ' + response.statusText);
                }
                return response.json();
            })
            .then(body => {
                serviceId = parseInt(body);
                localStorage.setItem('serviceId', serviceId)
                window.location.href = 'agendar.html'
                 // Redireciona para a página de agendamento
            })
                .catch(error => console.error('Error fetching service ID:', error));
        });
    }
});


document.addEventListener('DOMContentLoaded', function() {
    const dateInput = document.getElementById('date');
    const today = new Date();
    const yyyy = today.getFullYear();
    const mm = String(today.getMonth() + 1).padStart(2, '0'); 
    const dd = String(today.getDate()).padStart(2, '0');
    const todayFormatted = `${yyyy}-${mm}-${dd}`;

    dateInput.setAttribute('min', todayFormatted);
});


let userName;
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('useFormTime');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const date = document.getElementById('date').value;
            const time = document.getElementById('time').value;
            const serviceFind = parseInt(localStorage.getItem('serviceId'), 10);
            const userFind = parseInt(localStorage.getItem('userId'), 10);
            
            console.log(userFind);
            console.log(serviceFind);

            const scheduling = {
                user: { "id": userFind },
                date: date,
                time: time,
                service: { "id": serviceFind }
            };

            fetch('http://localhost:8080/scheduling/save', { 
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(scheduling)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na solicitação: ' + response.statusText);
                }
                return response.json().catch(() => ({}));
            })
            .then(data => {
                alert('Agendamento feito com sucesso!');
                window.location.href = 'agendamentos.html';
             })
            .catch(error => {
                console.error('Erro:', error);
                showCustomAlert('Este horário não está mais disponível!', 4000);
            });
        });
    }
});

function fetchUserScheduling() {
    const userId = localStorage.getItem('userId');
    if (!userId) {
        console.error('User ID não encontrado no localStorage');
        return;
    }

    fetch(`http://localhost:8080/scheduling/findByUserId?userId=${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na solicitação: ' + response.statusText);
            }
            return response.json();
        })
        .then(schedulings => {
            displaySchedulings(schedulings);
        })
        .catch(error => {
            console.error('Erro ao buscar agendamentos:', error);
        });
}
function displaySchedulings(schedulings) {
    const schedulingList = document.getElementById('schedulingList');
    schedulingList.innerHTML = ''; // Limpa a lista existente

    schedulings.forEach(scheduling => {
        const listItem = document.createElement('li');
        const listItem2 = document.createElement('li');
        const listItem3 = document.createElement('li');
        const listItem4 = document.createElement('li');
        listItem.textContent = `Data: ${scheduling.date}`;
        listItem2.textContent = `Hora: ${scheduling.time}`;
        listItem3.textContent = `Serviço: ${scheduling.typeService}`;
        listItem4.textContent = `Valor: ${scheduling.price}`;
        schedulingList.appendChild(listItem);
        schedulingList.appendChild(listItem2);
        schedulingList.appendChild(listItem3);
        schedulingList.appendChild(listItem4);
    });
}
document.addEventListener('DOMContentLoaded', function() {
    fetchUserScheduling();
});