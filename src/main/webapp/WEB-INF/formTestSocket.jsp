<%--
  Created by IntelliJ IDEA.
  User: valentinpitel
  Date: 05/11/2016
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- code client websocket -->
<script type="application/javascript">
    var ws = null;

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('echo').disabled = !connected;
    }

    function connect() {
        var target = document.getElementById('target').value;
        if (target == '') {
            alert('Please select server side connection implementation.');
            return;
        }
        if ('WebSocket' in window) {
            ws = new WebSocket(target);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(target);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }
        ws.onopen = function () {
            setConnected(true);
            log('Info: WebSocket connection opened.');
        };
        ws.onmessage = function (event) {
            log('Received: ' + event.data);
        };
        ws.onclose = function (event) {
            setConnected(false);
            log('Info: WebSocket connection closed, Code: ' + event.code + (event.reason == "" ? "" : ", Reason: " + event.reason));
        };
    }

    function disconnect() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
        setConnected(false);
    }

    function echo() {
        if (ws != null) {
            var message = document.getElementById('message').value;
            log('Sent: ' + message);
            ws.send(message);
        } else {
            alert('WebSocket connection not established, please connect.');
        }
    }

    function updateTarget(target) {
        if (window.location.protocol == 'http:') {
            document.getElementById('target').value = 'ws://' + window.location.host + target;
        } else {
            document.getElementById('target').value = 'wss://' + window.location.host + target;
        }
    }

    function log(message) {
        var console = document.getElementById('console');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        console.appendChild(p);
        while (console.childNodes.length > 25) {
            console.removeChild(console.firstChild);
        }
        console.scrollTop = console.scrollHeight;
    }


    document.addEventListener("DOMContentLoaded", function() {
        // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
        var noscripts = document.getElementsByClassName("noscript");
        for (var i = 0; i < noscripts.length; i++) {
            noscripts[i].parentNode.removeChild(noscripts[i]);
        }
    }, false);
</script>
<div class="noscript"><h2 style="color: #ff0000">il semble que le javascript ne fonctionne
    pas dans votre navigateur, veuillez verifier les paramètres</h2></div>
<!-- test socket de base -->
<div>
    <div id="connect-container">
        <div>
            <span>Se connecter au socket avec quelle classe ?</span>
            <!-- echo example sans anotation (coté serveur) -->
            <input id="radio1" type="radio" name="group1" value="/CompteurWeb/websocket/EchoEndpoint"
                   onclick="updateTarget(this.value);"/> <label for="radio1">programmatic API</label>
            <!-- echo example avec anotation (coté serveur) -->
            <input id="radio2" type="radio" name="group1" value="/CompteurWeb/websocket/EchoAnnotation"
                   onclick="updateTarget(this.value);"/> <label for="radio2">annotation API</label>
        </div>
        <div>
            <input id="target" type="text" size="40" style="width: 350px"/>
        </div>
        <div>
            <button id="connect" onclick="connect();">Connect</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
        </div>
        <div>
            <textarea id="message" style="width: 350px">Here is a message!</textarea>
        </div>
        <div>
            <button id="echo" onclick="echo();" disabled="disabled">Echo message</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"/>
    </div>
</div>
