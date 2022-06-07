$(document).ready(function () {
    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); //return New or Edit

        if (text === 'Edit') 
        {
            $.get(href, function (product) 
            {
                $('.myForm #productId').val(product.productId);
                $('.myForm #productName').val(product.productName);
                $('.myForm #price').val(product.price);
                $('.myForm #isReservable').prop('checked', product.reservable);
                $('.myForm #isPayable').prop('checked', product.payable);
                $('.myForm #posTerminalId').val(product.posTerminalId);
                $('.myForm #userId').val(product.user.userId);
            });
            $('.myForm #exampleModal').modal();
        } else {
            $('.myFormAdd #productName').val('');
            $('.myFormAdd #price').val('');
            $('.myFormAdd #isReservable');
            $('.myFormAdd #isPayable');
            $('.myFormAdd #posTerminalId').val('');
            $('.myFormAdd #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href);
        $('#myModal').modal();
    });
});
