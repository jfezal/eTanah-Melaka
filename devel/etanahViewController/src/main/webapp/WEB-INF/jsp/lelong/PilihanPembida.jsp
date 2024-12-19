<%-- 
    Document   : PilihanPembida
    Created on : Jul 1, 2010, 5:39:28 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.*,java.util.*" session="true"%>

<script type="text/javascript">
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
        $('#option').hide();
        $('#lain').hide();
        $('#lain2').hide();
    });


    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/maklumat_lelongan_awam?showForm3", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }


    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

    function refreshPage(){
        var url = '${pageContext.request.contextPath}/lelong/maklumat_perintah_jualan?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function edit1(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/lelong/maklumat_lelongan_awam?cetak&"+queryString+"&notis.idNotis="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function changeLain(value){
        if(value=="XI")
            $('#option').show();
        else
            $('#option').hide();
    }

    function changeLelong(value){
        if(value=="TL")
            $('#lain').show();
        else
            $('#lain').hide();

        if(value=="TM")
            $('#lain2').show();
        else
            $('#lain2').hide();
    }

    function calc(value){
        var deposit = value * 0.1;
        var baki = value - deposit;
        $("#deposit").val(deposit);
        $("#baki").val(baki);
        $("#deposit1").val(deposit);
        $("#baki1").val(baki);

        var num = document.getElementById('deposit').value;
        num = num.toString().replace(/\$|\,/g,'');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100+0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if(cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3))+','+
            num.substring(num.length-(4 * i + 3));
        $('#deposit').val((((sign)?'':'-') + num + '.' + cents));
    }

    function dateValidation(value){
        var now = new Date();
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var date = new Date(fulldate);
    <%--var now1 = (now.getMonth()+1)+'/'+(now.getDate()+30)+'/'+(now.getYear());//+1900);
    var now1New = new Date(now1);
    if(now1New > date){
        alert("Tarikh dimasukkan mestilah sama atau melebihi 30 hari dari tarikh sekarang.");
        $('#datepicker').val("");
        $('#datepicker').focus();
    }else{--%>
                var date1 = (date.getMonth()+1)+'/'+(date.getDate()+119)+'/'+(date.getYear());//+1900);
                var dateNew = new Date(date1);
                var sdate = dateNew.getDate()+'/'+(dateNew.getMonth()+1)+'/'+(dateNew.getYear());//+1900);
                $('#tarikhBayar').val(sdate);
                $('#tarikhBayar1').val(sdate);

    <%--}--%>
            }

            function validate(input) {
                if (input.length == 0) {
                    alert ('Sila Masukkan Harga Rizab');
                    $('#ejaRizab').val("");
                    return true;
                }
                else convert(input);

                var num = document.getElementById('hargaRizab').value;
                num = num.toString().replace(/\$|\,/g,'');
                if (isNaN(num))
                    num = "0";
                sign = (num == (num = Math.abs(num)));
                num = Math.floor(num * 100+0.50000000001);
                cents = num % 100;
                num = Math.floor(num / 100).toString();
                if(cents < 10)
                    cents = "0" + cents;
                for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
                    num = num.substring(0, num.length - (4 * i + 3))+','+
                    num.substring(num.length-(4 * i + 3));
                $('#hargaRizab').val((((sign)?'':'-') + num + '.' + cents));
            }
</script>

<s:form beanclass="etanah.view.stripes.lelong.LelonganAwamActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>

    <fieldset class="aras1">
        <p>
            <label>Status lelongan :</label>
            <c:if test="${actionBean.lelong.bil eq '1'}">
                Kali Pertama
            </c:if>
            <c:if test="${actionBean.lelong.bil eq '2'}">
                Kali Kedua
            </c:if>
            <c:if test="${actionBean.lelong.bil eq '3'}">
                Kali Ketiga
            </c:if>
        </p>
        <br>
        <legend>
            Rekod Bidaan
        </legend>
        <div class="subtitle displaytag">
            <p>
                <label>Pilihan :</label>
                <s:radio name="" value="I" onclick="javaScript:changeLain(this.value)"/>Ada Pembida &nbsp;
                <s:radio name="" value="XI" onclick="javaScript:changeLain(this.value)"/> Tiada Pembida
            </p>
        </div>
        <div class="subtitle" id="option">
            <p>
                <label>Lelongan :</label>
                <s:select id="" name="" style="width:200px;">
                    <s:option value="" onclick="javaScript:changeLelong(this.value)">--Sila Pilih--</s:option>
                    <s:option value="TL" onclick="javaScript:changeLelong(this.value)">Teruskan Lelongan</s:option>
                    <s:option value="TM" onclick="javaScript:changeLelong(this.value)">Tamatkan Lelongan</s:option>
                </s:select>
            </p>
        </div>
        <div class="subtitle" id="lain">
            <fieldset class="aras1">
                <legend>
                    Maklumat Perintah Jualan
                </legend>
                <div class="content">
                    <div class="subtitle">
                        <div class="content">
                            <p>
                                <label>Tarikh Lelongan Awam:</label>
                                <s:text name="tarikhLelong" id="datepicker" class="datepicker" onchange="dateValidation(this.value)"/>&nbsp;
                                <a href="javascript:show_calendar();">
                                    <img src="${pageContext.request.contextPath}/pub/images/calendar.gif" id="" width=15 height=15 /></a>
                                <font color="red" size="1">(cth : hh / bb / tttt)</font> 
                                &nbsp;
                            </p>
                            <%--                            <p>
                                                            <label>Tarikh Lelongan Awam:</label>
                                                            <s:text name="tarikhLelong" id="datepicker" class="datepicker" onchange="dateValidation(this.value)"/>&nbsp;
                                                            <font color="red" size="1">(cth : hh / bb / tttt)</font> (Tidak kurang dari 30 hari dari tarikh sekarang)
                                                            &nbsp;
                                                        </p>--%>
                            <p>
                                <label><font color="red">*</font>Masa Lelongan Awam :</label>
                                <s:select name="jam" style="width:56px">
                                    <s:option value="00">Jam</s:option>
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
                                </s:select>:
                                <s:select name="minit" style="width:65px">
                                    <s:option value="00">Minit</s:option>
                                    <s:option value="00">00</s:option>
                                    <s:option value="05">05</s:option>
                                    <s:option value="10">10</s:option>
                                    <s:option value="15">15</s:option>
                                    <s:option value="20">20</s:option>
                                    <s:option value="25">25</s:option>
                                    <s:option value="30">30</s:option>
                                    <s:option value="35">35</s:option>
                                    <s:option value="40">40</s:option>
                                    <s:option value="45">45</s:option>
                                    <s:option value="50">50</s:option>
                                    <s:option value="55">55</s:option>
                                </s:select>:
                                <s:select name="ampm" style="width:80px">
                                    <s:option value="0">Pilih</s:option>
                                    <s:option value="AM">Pagi</s:option>
                                    <s:option value="PM">Petang</s:option>
                                </s:select>

                            <p>
                                <label><font color="red"></font>Tempat :</label>
                                <s:select name="" style="width:200px" >
                                    <s:option value=""></s:option>
                                    <s:option value=""></s:option>
                                </s:select>
                            </p>
                            <p>
                                <label>Harga Rizab (RM): </label>
                                <s:text id="hargaRizab" name="lelong.hargaRizab" value="0.00" onblur="validate(this.value)" onkeyup="validateNumber(this,this.value);" onchange="calc(this.value);"  formatPattern="###,###,###.00"/>&nbsp;
                            </p>

                            <p>
                                <label>Harga Rizab Dalam Perkataan:</label>
                                <s:textarea name="lelong.ejaanHarga" cols="40" rows="3" /> Ringgit &nbsp;
                            </p>
                            <p>
                                <label>Tarikh Akhir Bayaran : </label>
                                <s:text id="tarikhBayar" name="lelong.tarikhAkhirBayaran" formatPattern="dd/MM/yyyy" disabled="true" />&nbsp;(120 hari dari tarikh lelongan)
                                <s:hidden name="tarikhAkhirBayaran" id="tarikhBayar1"/>
                            </p>

                            <c:if test="${actionBean.negori eq true}">
                                <p>
                                    <label>Amaun Hutang Tertunggak : </label>
                                    <fmt:formatNumber value="${actionBean.amaunTunggakan}" pattern="###,###,###.00"/>

                                </p>
                            </c:if>

                            <legend>
                                Sejarah Lelongan
                            </legend>
                            <div class="content" align="center">
                                <display:table class="tablecloth" name="${actionBean.senaraiLelongan}" id="line">
                                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                                    <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                                    <display:column title="Harga Rezab (RM)">
                                        <div align="right">
                                            <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                                        </div>
                                    </display:column>
                                    <display:column property="kodStatusLelongan.nama" title="Status" class=""/>
                                    <display:column property="" title="Ulasan" class=""/>
                                </display:table>
                            </div>
                            <c:if test="${actionBean.disabled eq null}">
                                <p align="right">
                                    <s:button name="simpanLelong" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                    <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                                </p>
                            </c:if>
                            <c:if test="${actionBean.disabled ne null}" >
                                <p align="right">
                                    <s:button name="cetak" id="save" value="Cetak Sijil Rujukan" class="longbtn" onclick="edit1(this.form, this.name, 'page_div');"/>
                                </p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </fieldset>
            <br>
        </div>
    </fieldset>

    <div class="subtitle" id="lain2">
        <fieldset class="aras1">
            <p align="right">
                <s:button name="cetak" id="save" value="Cetak Sijil Rujukan" class="longbtn" onclick="edit1(this.form, this.name, 'page_div');"/>
            </p>
        </fieldset>
    </div>
</s:form>