<%-- 
    Document   : sediaBorangQ
    Created on : Jun 1, 2010, 12:20:34 PM
    Author     : massita
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $(".datepicker1").datepicker({dateFormat: 'yy'});
        $('.tempoh').show();
    });

    function updateTotal (inputTxt)
    {
        var total = 0;
        var bil = ${actionBean.pampasan};
        for (var i = 0; i <bil; i++){
            var a = document.getElementById('form' + i)
            if (a == null) break;
            if ((isNaN(a.value))||((a.value) ==""))
            {
                alert("Nombor tidak sah");
                inputTxt.value = RemoveNonNumeric(a);
                $('#form').val("0.00");
                return;
            }
            total += parseFloat(a.value);
        }
        var t = document.getElementById('form');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function maxMonth() {
        if($("#bulan").val() == ""){
            alert('sila masukkan tempoh bulan terlebih dahulu.');
            $("#bulan").focus();
            return false;
        }
        if ($("#bulan").val()>36){
            alert('Tempoh Pendudukan mestilah Tidak Lebih dari 36 bulan');
            $("#bulan").focus();
            return false;
        }

        if ($("#tkhMula").val() == ""){
            alert ('Sila Masukkan Tarikh Mula Menduduki Tanah.');
            $('#tkhMula').focus();
            return false;
        }

        if ($("#tkhTamat").val() == ""){
            alert ('Sila Masukkan Tarikh Tamat Pendudukan.');
            $("#trhTamat").focus();
            return false;
        }
        return true;
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function doCalcEndDate(id){
        var hari = parseInt($('.hari').val(),10);
        var bln = parseInt($('.bulan').val(),10);
        var thn = parseInt($('.tahun').val(),10);

        if(isNaN(hari) && isNaN(bln) && isNaN(thn)){
            alert('Sila Masukan Tempoh.');
            $(id).val('');
            return;
        }
        var startDate = $('#' +id).val();
        //manual process :: should find conclusion on this
        var y = parseInt(startDate.substring(6, 10),10);
        var m = parseInt(startDate.substring(3, 5),10);
        var d = parseInt(startDate.substring(0, 2),10);
        var r = new Date();
        //alert(y);
        //alert(m);
        //alert(d);
        //r.setFullYear(y, m, d);

        if(!isNaN(hari)){
            d = d + hari;
            d = d -1;
            if (d ==0){m = m-1;}
        }

        if(!isNaN(bln)){
            m = m + bln;
            if(m > 12){
                y = y +1;
                m = m - 12;
                if (m == 2){
                    var isleap = (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
                    if (d>=30){
                        if (isleap) {
                            d = 28;
                        } else {
                            d = 27;
                        }
                    }else if (d ==0 ){
                        if (isleap) {
                            d = 29;
                        } else {
                            d = 28;
                        }
                    }
                }
            }
        }

        if(!isNaN(thn)){
            y = y + thn;
        }

        if (d == 0 && m == 0) {
            y = y - 1;
        }
        //y = y + thn;
        var endDate = new Date();
        endDate.setDate(d);
        endDate.setMonth(m-1);
        endDate.setFullYear(y);
        $('#tkhTamat').val(endDate.format("dd/mm/yyyy"));
    }

    function test(){
        document.getElementById("bulan").value ="";
        document.getElementById("tkhMula").value ="";
        document.getElementById("maksud").value ="";
        document.getElementById("pampasan").value ="";
        document.getElementById("tarikhHadir").value ="";
        document.getElementById("waktuHadir").value ="";
    }
    function kiraBln(){
        var mula = document.getElementById("tkhMula").value;
        var tmt =  document.getElementById("tkhTamat").value;
        var tmt2 =  document.getElementById("tkhTamat");
        
        day1 = mula.substring (0, mula.indexOf ("/"));
        month1 = mula.substring (mula.indexOf ("/")+1, mula.lastIndexOf ("/"));
        year1 = mula.substring (mula.lastIndexOf ("/")+1, mula.length);
        
        day2 = tmt.substring (0, tmt.indexOf ("/"));
        month2 = tmt.substring (tmt.indexOf ("/")+1, tmt.lastIndexOf ("/"));
        year2 = tmt.substring (tmt.lastIndexOf ("/")+1, tmt.length); 

        date1 = year1+"/"+month1+"/"+day1;
        date2 = year2+"/"+month2+"/"+day2;
        firstDate = Date.parse(date1);
        secondDate= Date.parse(date2);

        msPerDay = 24 * 60 * 60 * 1000
        dbd = Math.round((secondDate.valueOf()-firstDate.valueOf())/ msPerDay) + 1;
        hari3Thn=360*3;
        if(dbd>hari3Thn){
            alert("Tempoh diantara tarikh mula dan tarikh tamat melebihi 3 tahun");
            tmt2.value="";
            return false;
        }
         return true;
        // var months = (tmt.getFullYear() - mula.getFullYear()) * 12;
        // alert(months);
    }
    
   
</script>

<div class="subtitle">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pengambilan.PendudukanSementaraBorangQActionBean" id="form">
        <s:messages/>
        <s:errors/>
        <fieldset class="aras1">
            <legend align="justify">Pemberitahu Menduduki atau Menggunakan Tanah Bagi Sementara</legend>

            <div class="content" align="left">
                <table border="0" cellspacing="5" width="1%">
                    <br />
                    <p class ="tempoh">
                        <label for="nama pihak">Tempoh :</label>
                        <s:text name="tempoh" class="bulan" id="bulan" onchange="maxMonth()" onkeyup="validateNumber(this,this.value);"/> bulan &nbsp;
                    </p>

                    <p>
                        <label for="nama pihak">Tarikh Mula Menduduki Tanah :</label>
                        <s:text name="tarikhMula" class="datepicker" formatPattern="dd/MM/yyyy" id="tkhMula" onchange="doCalcEndDate(this.id);"/>
                    </p>

                    <p class="tempoh">
                        <label for="nama pihak">Tarikh Tamat Pendudukan :</label>
                        <s:text name="tkhTamat" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tkhTamat" onchange="kiraBln()"/>
                    </p>
                    <c:if test="${maksud}">
                        <tr>
                            <td valign="top"><label>Maksud :</label></td>
                            <td><s:textarea name="maksud" rows="3" cols="50" id="maksud" class="normal_text"/></td>
                        </tr>
                    </c:if>

                    <c:if test="${pampasan}">
                        <tr>
                            <td><label>Tawaran/Rundingan Pampasan :</label></td>
                            <td>RM&nbsp;<s:text name="pampasan" id="pampasan" style="text-align:left" formatPattern="###,###,###.00" onkeyup="validateNumber(this,this.value);"/></td>
                        </tr>
                    </c:if>

                    <%--<c:if test="${tarikhHadir}">--%>
                    <tr>
                        <td><label>Tarikh Hadir :</label></td>
                        <td><s:text name="tarikhHadir" id="tarikhHadir" class="datepicker" size="12" formatPattern="dd/MM/yyyy"/></td>
                    </tr>
                    <tr>
                        <td><label>Masa :</label></td>
                        <td>
                            <%--<c:if test="${waktuHadir}">--%>
                            <s:select name="jam" style="width:60px" id="jam">
                                <s:option>Jam</s:option>
                                <s:option value="00">00</s:option>
                                <s:option value="01">01</s:option>
                                <s:option value="02">02</s:option>
                                <s:option value="03">03</s:option>
                                <s:option value="04">04</s:option>
                                <s:option value="05">05</s:option>
                                <s:option value="06">06</s:option>
                                <s:option value="07">07</s:option>
                                <s:option value="08">08</s:option>
                                <s:option value="09">09</s:option>
                                <s:option value="10">10</s:option>
                                <s:option value="11">11</s:option>
                                <s:option value="12">12</s:option>
                            </s:select>
                            <s:select name="minit" style="width:62px" id="minit">
                                <s:option>Minit</s:option>
                                <s:option value="00">00</s:option>
                                <s:option value="01">01</s:option>
                                <s:option value="02">02</s:option>
                                <s:option value="03">03</s:option>
                                <s:option value="04">04</s:option>
                                <s:option value="05">05</s:option>
                                <s:option value="06">06</s:option>
                                <s:option value="07">07</s:option>
                                <s:option value="08">08</s:option>
                                <s:option value="09">09</s:option>
                                <s:option value="10">10</s:option>
                                <s:option value="11">11</s:option>
                                <s:option value="12">12</s:option>
                                <s:option value="13">13</s:option>
                                <s:option value="14">14</s:option>
                                <s:option value="15">15</s:option>
                                <s:option value="16">16</s:option>
                                <s:option value="17">17</s:option>
                                <s:option value="18">18</s:option>
                                <s:option value="19">19</s:option>
                                <s:option value="20">20</s:option>
                                <s:option value="21">21</s:option>
                                <s:option value="22">22</s:option>
                                <s:option value="23">23</s:option>
                                <s:option value="24">24</s:option>
                                <s:option value="25">25</s:option>
                                <s:option value="26">26</s:option>
                                <s:option value="27">27</s:option>
                                <s:option value="28">28</s:option>
                                <s:option value="29">29</s:option>
                                <s:option value="30">30</s:option>
                                <s:option value="31">31</s:option>
                                <s:option value="32">32</s:option>
                                <s:option value="33">33</s:option>
                                <s:option value="34">34</s:option>
                                <s:option value="35">35</s:option>
                                <s:option value="36">36</s:option>
                                <s:option value="37">37</s:option>
                                <s:option value="38">38</s:option>
                                <s:option value="39">39</s:option>
                                <s:option value="40">40</s:option>
                                <s:option value="41">41</s:option>
                                <s:option value="42">42</s:option>
                                <s:option value="43">43</s:option>
                                <s:option value="44">44</s:option>
                                <s:option value="45">45</s:option>
                                <s:option value="46">46</s:option>
                                <s:option value="47">47</s:option>
                                <s:option value="48">48</s:option>
                                <s:option value="49">49</s:option>
                                <s:option value="50">50</s:option>
                                <s:option value="51">51</s:option>
                                <s:option value="52">52</s:option>
                                <s:option value="53">53</s:option>
                                <s:option value="54">54</s:option>
                                <s:option value="55">55</s:option>
                                <s:option value="56">56</s:option>
                                <s:option value="57">57</s:option>
                                <s:option value="58">58</s:option>
                                <s:option value="59">59</s:option>
                                <s:option value="60">60</s:option>
                            </s:select>
                            <s:select name="pagiPetang" style="width:80px" id="pagiPetang">
                                <s:option>Pilih</s:option>
                                <s:option value="AM">AM</s:option>
                                <s:option value="PM">PM</s:option>
                            </s:select>
                            <%--</c:if>--%>
                        </td>
                    </tr>

                </table>
            </div>
        </fieldset><br />
        <div align="center">
            <tr>
                <td>
                    <%--nnt bkk<s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>--%>
                    <s:button class="btn" onclick="if(maxMonth())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                    <s:button name="reset" value="Isi Semula" class="btn" onclick="test();"/>
                </td>
            </tr>
        </div>
    </div>
</s:form>
