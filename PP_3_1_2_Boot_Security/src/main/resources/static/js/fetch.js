$(function() {
    drawUsersList();
    addUser();

    editModal();
    deleteModal();
});

async function drawUsersList() {
    let usersJson = (await fetchService.findAll()).json();

    usersJson.then(
        users => {
            console.log(users);
            $('#usersTable tbody').empty();
            users.forEach(user => {
                let userRoles = ``
                for(let role of user.roles) {
                    userRoles += role.role.replace("ROLE_", "") + ', '
                }

                let userRow = `$(<tr>
                                <th scope="row">${user.id}</th>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td>${user.age}</td>
                                <td>${userRoles.slice(0, -2)}</td>
    
                                <td>
                                    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#defaultModal" id="editButton"
                                data-id="${user.id}" data-action="editUser">
                                            Edit
                                </button>
                                </td>
    
                                <td>
                                    <button type="button" class="btn btn-danger btn-primary" data-bs-toggle="modal"
                                          data-bs-target="#deleteModal" data-id="${user.id}" data-action="deleteUser">
                                    Delete
                                  </button>
                                </td>
                            </tr>)`;

                $('#usersTable tbody').append(userRow);
            })
        }
    )
}

async function addUser() {

    let roleJson = (await fetchService.findAll_roles()).json();

    roleJson.then(roles => {
        roles.forEach(role => {
            console.log(role);
            addUserForm.find('#newRoles').append(new Option(role.role.replace("ROLE_", ""), role.id));
        })
    })


    let addUserForm = $('#addUserForm');
    $('#addUser').on('click', async e => {

        let username = addUserForm.find('#newUsername').val().trim();
        let password = addUserForm.find('#newPassword').val().trim();
        let age = addUserForm.find('#newAge').val().trim();
        let email = addUserForm.find('#newEmail').val().trim();
        let rolesFromForm = addUserForm.find('#newRoles').val();
        console.log(rolesFromForm);

        let selectedRoles = [];
        await roleJson.then(roles => {
            roles.forEach(role => {
                rolesFromForm.forEach(roleId => {
                    if (role.id == roleId) {
                        selectedRoles.push(role);
                    }
                });
            });
        });

        let data = {
            username: username,
            password: password,
            age: age,
            email: email,
            roles: selectedRoles
        }

        console.log(data);

        const addResponse = await fetchService.add(data);
        if (addResponse.ok) {
            drawUsersList();

            let selectors = ['#newUsername', '#newPassword', '#newAge', '#newEmail', '#newRoles'];
            selectors.forEach(selector => {
                addUserForm.find(selector).val('');
            });
        } else {
        }
    })

}

function editModal() {
    $('#defaultModal').modal({
        keyboard: true,
        backdrop: "static",
        show: false,
    }).on("show.bs.modal", function(event){
        let button = $(event.relatedTarget);
        let id = button.data('id');
        let action = button.data('action');
        editUser($(this), id);
    }).on('hidden.bs.modal', function(event){

    });
}

function deleteModal() {
    $('#deleteModal').modal({
        keyboard: true,
        backdrop: "static",
        show: false,
    }).on("show.bs.modal", function(event){
        let button = $(event.relatedTarget);
        let id = button.data('id');
        deleteUser($(this), id);
    }).on('hidden.bs.modal', function(event){

    });
}

async function editUser(modal, id) {
    const response = await fetchService.findById(id);
    const rolesResponse = await fetchService.findAll_roles();
    const rolesJson = rolesResponse.json();
    const userJson = response.json();
    let prevPass = "";

    modal.show();

    // заполняем форму данными юзера
    userJson.then(user => {
        modal.find('#id').val(user.id).prop('disabled', true);
        modal.find('#username').val(user.username);
        modal.find('#age').val(user.age);
        modal.find('#email').val(user.email);
        // modal.find('#password').val(user.password);
        modal.find('#roles').empty();

        prevPass = user.password;

        rolesJson.then(roles => {
            roles.forEach(role => {
                user.roles.forEach(userRole => {
                    if (userRole.id == role.id) {
                        modal.find('#roles').append(new Option(role.role.replace("ROLE_", ""), role.id, false, true));
                        role = true;
                    }
                });
                (role !== true) ? modal.find('#roles').append(new Option(role.role.replace("ROLE_", ""), role.id)) : null;
            });
        });
    });

    $('#updateUserButton').click(async function(e){
        let id = modal.find('#id').val().trim();
        let username = modal.find('#username').val().trim();
        let password = modal.find('#password').val().trim();
        if (password === "") {
            password = prevPass;
        }
        let age = modal.find('#age').val().trim();
        let email = modal.find('#email').val().trim();
        let rolesFromForm = modal.find('#roles').val();

        let userRoles = [];
        await rolesJson.then(roles => {
            roles.forEach(role => {
                rolesFromForm.forEach(roleId => roleId == role.id ? userRoles.push(role) : null)
            });
        });

        let data = {
            id: id,
            username: username,
            password: password,
            age: age,
            email: email,
            roles: userRoles
        };

        const userResponse = await fetchService.update(id, data);

        if (userResponse.status == 200) {
            await drawUsersList();
            $('#defaultModal').modal('hide');
        } else {
        }
    });
}

async function deleteUser(modal, id) {
    const userResponse = await fetchService.findById(id);
    const userJson = userResponse.json();

    modal.show();

    userJson.then(user => {
        modal.find('#id').val(user.id);
        modal.find('#username').val(user.username).prop('disabled', true);
        modal.find('#password').parent().remove();
        modal.find('age').val(user.age).prop('disabled', true);
        modal.find('email').val(user.email).prop('disabled', true);
        modal.find('#roles').empty();

        user.roles.forEach(userRole => {
            modal.find('#roles').append(new Option(userRole.role.replace("ROLE_", ""), userRole.id, false, true));
        })
        modal.find('#roles').prop('disabled', true);
    })


    $('#deleteUserButton').click(async function(e){
        e.preventDefault();
        const userResponse = await fetchService.delete(id);

        if (userResponse.ok) {
            $('#deleteModal').modal('hide');
            await drawUsersList();
        } else {
        }
    });
}

const http = {
    fetch: async function(url, options = {}) {
        const response = await fetch(url, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            ...options,
        });
        return response;
    }
};


const fetchService = {
    findAll: async () => {
        return await http.fetch('/api/v1/users');
    },
    findAll_roles: async () => {
        return await http.fetch('/api/v1/roles');
    },
    add: async (data) => {
        return await http.fetch('/api/v1/users', {
            method: 'POST',
            body: JSON.stringify(data)
        });
    },
    findById: async (id) => {
        return await http.fetch('/api/v1/users/' + id);
    },
    update: async (id, data) => {
        return await http.fetch('/api/v1/users/update/' + id, {
            method: 'POST',
            body: JSON.stringify(data)
        });
    },
    delete: async (id) => {
        return await http.fetch('/api/v1/users/delete/' + id, {
            method: 'POST'
        });
    },
};