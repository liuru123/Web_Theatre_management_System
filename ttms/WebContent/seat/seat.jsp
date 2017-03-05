<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <title>选座购票</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/jquery.seat-charts.css">
    <link rel="stylesheet" type="text/css" href="style.css">
      <link rel="stylesheet" type="text/css" href="index.css">
    <script src="bootstrap/js/jquery-1.11.0.min.js"></script>
    <script src="bootstrap/js/jquery.seat-charts.min.js"></script>
</head>
<body id=body3>
<div class="wrapper">
    <div class="container">
        <div id="seat-map">
            <div class="front-indicator">屏幕</div>
        </div>
    </div>

    <script>
        var firstSeatLabel = 1;

        $(document).ready(function () {
            var $cart = $('#selected-seats'),
                    $counter = $('#counter'),
                    $total = $('#total'),
                    sc = $('#seat-map').seatCharts({
                        map: [
                            'fffffffffffffffff',
                            'fffffffffffffffff',
                            'eeeeeeeeeeeeeeeee',
                            'eeeeeeeeeeeeeeeee',
                            'eeeeeeeeeeeeeeeee',
                            'eeeeeeeeeeeeeeeee',
                            'eeeeeeeeeeeeeeeee',
                            'eeeeeeeeeeeeeeeee',
                            'eeeeeeeeeeeeeeeee',
                            'eeeeeeeeeeeeeeeee',
                        ],
                        seats: {
                            f: {
                                price: 100,
                                classes: 'first-class', //your custom CSS class
                                category: 'vip'
                            },
                            e: {
                                price: 40,
                                classes: 'economy-class', //your custom CSS class
                                category: '普通座位'
                            }

                        },
                        naming: {
                            top: false,
                            getLabel: function (character, row, column) {
                                return firstSeatLabel++;
                            },
                        },
                        legend: {
                            node: $('#legend'),
                            items: [
                                ['f', 'available', 'vip'],
                                ['e', 'available', '普通座位'],
                                ['f', 'unavailable', '已预定']
                            ]
                        },
                        click: function () {
                            if (this.status() == 'available') {
                                $('<li>' + this.data().category + this.settings.label + '号座位' + '：<br/>价格：<b>$' + this.data().price + '</b> <a href="#" class="cancel-cart-item">[删除]</a></li>')
                                        .attr('id', 'cart-item-' + this.settings.id)
                                        .data('seatId', this.settings.id)
                                        .appendTo($cart);
                                $counter.text(sc.find('selected').length + 1);
                                $total.text(recalculateTotal(sc) + this.data().price);

                                return 'selected';
                            } else if (this.status() == 'selected') {
                                //update the counter
                                $counter.text(sc.find('selected').length - 1);
                                //and total
                                $total.text(recalculateTotal(sc) - this.data().price);

                                //remove the item from our cart
                                $('#cart-item-' + this.settings.id).remove();

                                //seat has been vacated
                                return 'available';
                            } else if (this.status() == 'unavailable') {
                                //seat has been already booked
                                return 'unavailable';
                            } else {
                                return this.style();
                            }
                        }
                    });

            //this will handle "[cancel]" link clicks
            $('#selected-seats').on('click', '.cancel-cart-item', function () {
                //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
                sc.get($(this).parents('li:first').data('seatId')).click();
            });


        });
        function recalculateTotal(sc) {
            var total = 0;

            sc.find('selected').each(function () {
                total += this.data().price;
            });

            return total;
        }
    </script>
</div>
</body>
</html>
