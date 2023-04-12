(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!validateFields(form) || !form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                } else form.classList.add('was-validated')
            }, false)
            form.addEventListener('change', function (event) {
                if (!validateFields(form) || !form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
            }, false)
        });
    function validateFields(form) {
        var validate = true
        var fields = document.querySelectorAll('.form-control')
        Array.prototype.slice.call(fields)
            .forEach(function (field) {
                if (field.minLengt != '' && field.value.length < field.minLength) {
                    validate = false
                    field.classList.remove("is-valid")
                    field.classList.add("is-invalid")
                } else {
                    field.classList.remove("is-invalid")
                    field.classList.add("is-valid")
                }
            });
        return validate
    }

    $(document).ready(function(){
        $('.link-tr').each(function() {
            var $th = $(this);
            $th.on('click', function() {
                window.location.href = $th.attr('data-url');
            });
        });
    });

})()
