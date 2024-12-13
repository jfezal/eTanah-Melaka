<%--
    Document   : tawaran_kompaun_syor
    Created on : May 13, 2011, 11:52:22 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
<c:if test="${multipleOp}">
    <style type="text/css">
        .tablecloth{
            padding:0px;
            width:90%;
        }

        .infoLP{
            float: right;
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;

        }

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        var a = 0.00;
        var bil = ${actionBean.rowCount};
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('amountSyor'+i).value;
            if(amount !=""){
                document.getElementById("pilih"+i).checked = true;
                document.getElementById("pilih"+i).disabled = true;
                a += parseFloat(amount);
                document.getElementById('jumCaraBayar').value=a;

            }
        }
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageKompaun();
            self.close();
        },'html');

    }

    function save1(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageMuktamad();
            self.close();
        },'html');

    }

    function save2(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageBayaranPelupusan();
            self.close();
        },'html');

    }

    function save3(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageMuktamadkompaunMelakaOp();
            self.close();
        },'html');

    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function totalAmount(){
        var total = 0;
        var bil = ${actionBean.rowCount};
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('amountSyor'+i).value;
            if(amount !=""){
                total += parseFloat(amount);
                document.getElementById('amountSyor'+i).value= parseFloat(amount).toFixed(2);
                document.getElementById('jumCaraBayar').value=parseFloat(total).toFixed(2);
            }
        }
    }
    
    function totalAmountKompaunOks(){

        var total = 0;
        var bil = ${fn:length(actionBean.senaraiOKSForKompaun)};
        //alert("bil totalAmountKompaunOks : "+bil);
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('amountSyor'+i).value;
            if(amount !=""){
                total += parseFloat(amount);
                document.getElementById('amountSyor'+i).value= parseFloat(amount).toFixed(2);
                document.getElementById('jumCaraBayar').value=parseFloat(total).toFixed(2);
            }
        }
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

    function validateForm(){

        if(document.form1.tempoh.value=="")
        {
            alert("Sila Isi Tempoh");
            $('#tempoh').focus();
            return false;
        }
    <c:if test="${!multipleOp}">

            if(document.form1.orangKenaSyak.value=="")
            {
                alert("Sila pilih maklumat Isu Kepada");
                $('#orangKenaSyak').focus();
                return false;
            }
    </c:if>
            var bil =  ${fn:length(actionBean.senaraiBarangRampasan)};
            var j = 0;
            for (var i = 0; i < bil; i++){

                var pilih = document.getElementById('pilih'+i).checked;
                var amountSyor = document.getElementById('amountSyor'+i).value;

                if( pilih == false && amountSyor!=""){
                    alert("Sila tanda di dalam kotak pilih sebelum simpan syor kompaun yang dikehendaki");
                    $('#pilih'+i).focus();
                    return false;
                }
                if( pilih == true && amountSyor==""){
                    alert("Sila masukkan syor kompaun");
                    $('#amountSyor'+i).focus();
                    return false;
                }
 
                if( pilih == true){
                    j++;
                }

            }
            
            if(j == 0 ){
                alert("Sila pilih barang rampasan untuk dikenakan kompaun ");
                return false;
            }
 
            return true;
        }

        function test(f) {
            $(f).clearForm();
        }
        
        function validateFormOKS(){

            if(document.form1.tempoh.value=="")
            {
                alert("Sila Isi Tempoh");
                $('#tempoh').focus();
                return false;
            }

            var bil =  ${fn:length(actionBean.senaraiOKSForKompaun)};
            var j = 0;
            for (var i = 0; i < bil; i++){

                var pilih = document.getElementById('pilih'+i).checked;
                var amountSyor = document.getElementById('amountSyor'+i).value;

                if( pilih == false && amountSyor!=""){
                    alert("Sila tanda di dalam kotak pilih sebelum simpan syor kompaun yang dikehendaki");
                    $('#pilih'+i).focus();
                    return false;
                }
                if( pilih == true && amountSyor==""){
                    alert("Sila masukkan syor kompaun");
                    $('#amountSyor'+i).focus();
                    return false;
                }
 
                if( pilih == true){
                    j++;
                }

            }
            
            if(j == 0 ){
                alert("Sila pilih oks/suspek untuk dikenakan kompaun ");
                return false;
            }
 
            return true;
        }

</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKompaunActionBean" name="form1">
    <s:messages />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tawaran Kompaun 
            </legend>
            <div class="content">
                <p>
                    <label>Tempoh (Hari) :</label>
                    <s:text name="tempohKompaun" onkeyup="validateNumber(this,this.value);" maxlength="2" id="tempoh"/>&nbsp;
                </p>
                <c:if test="${!multipleOp}">
                    <p>
                        <label>Isu Kepada :</label>
                        <s:select name="orangKenaSyak" id="orangKenaSyak">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.senaraiAduanOrangKenaSyak}" label="nama" value="idOrangKenaSyak"/>
                        </s:select>
                    </p>
                </c:if>

                <p>
                <fieldset class="aras2">
                    <legend>
                        Barang Rampasan
                    </legend>
                    <div class="instr-fieldset">
                        Sila pilih Orang Yang Disyaki yang akan dikenakan kompaun
                    </div>

                    <div align="center" >
                        <c:if test="${multipleOp}">
                            <display:table class="tablecloth" name="${actionBean.senaraiOKSForKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" style="width:90%;">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Pilih">
                                    <s:checkbox name="pilih${line_rowNum-1}" id="pilih${line_rowNum-1}" value="${line.idOrangKenaSyak}"/>
                                </display:column>
                                <display:column title="Nama Suspek" property="nama"/>
                                <display:column title="Maklumat Barang Rampasan" style="width:50%;">
                                    <c:set value="0" var="j"/>
                                    <c:set value="0" var="totalBarangRampasan"/>
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="5%" align="center"><b>Item</b></th>
                                            <th  width="5%" align="center"><b>Kuantiti</b></th>
                                            <th  width="5%" align="center"><b>Tarikh Rampasan</b></th>

                                        </tr>
                                        <c:set value="1" var="b"/>
                                        <c:forEach items="${actionBean.senaraiBarangRampasan}" var="barang">
                                            <c:if test="${barang.aduanOrangKenaSyak.idOrangKenaSyak eq line.idOrangKenaSyak}">
                                                <tr>
                                                    <td width="5%">${b}</td>
                                                    <td width="30%" style="font-size: 12px;">${barang.item}</td>
                                                    <td width="10%" style="font-size: 12px;">
                                                        <c:if test="${barang.kodKategoriItemRampasan.kod eq 'K'}">
                                                            1
                                                        </c:if>
                                                        <c:if test="${barang.kodKategoriItemRampasan.kod eq 'B'}">
                                                            ${barang.kuantiti} ${barang.kuantitiUnit}
                                                        </c:if> 
                                                    </td>
                                                    <td width="50%" style="font-size: 12px;">
                                                        <fmt:formatDate pattern="dd/MM/yyyy" value="${barang.tarikhRampas}"/> <br>
                                                    </td>
                                                </tr>
                                                <c:set value="${b+1}" var="b"/>
                                            </c:if>
                                        </c:forEach>

                                    </table>
                                </display:column>
                                <display:column title="Muktamad Kompaun (RM)">
                                    <s:text name="amaunMuktamadKompaun${line_rowNum-1}" class="number" formatPattern="0.00" id="amountSyor${line_rowNum-1}" size="10" maxlength="14" onblur="totalAmountKompaunOks();" onkeyup="validateNumber(this,this.value);"/>
                                </display:column>
                                <display:footer>
                                    <tr>
                                        <td colspan="4" align="right">Jumlah(RM):</td>
                                        <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="10"
                                                   class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                    <tr>
                                    </display:footer>
                                </display:table>
                            </c:if>
                            <c:if test="${!multipleOp}">
                                <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                    <display:column title="Pilih">
                                        <s:checkbox name="pilih${line_rowNum-1}" id="pilih${line_rowNum-1}" value="${line.idBarang}"/>
                                        <%--<s:hidden name="senaraiBarangRampasan[${line_rowNum-1}].idBarang" value="${line.idBarang}"/>--%>
                                    </display:column>
                                    <display:column title="Nama Suspek" property="aduanOrangKenaSyak.nama"/>
                                    <display:column title="Barang Rampasan"><a class="popup" onclick="popup(${line.idBarang})">${line.item}</a></display:column>
                                <display:column title="Kuantiti">
                                    <c:if test="${line.kodKategoriItemRampasan.kod eq 'K'}">
                                        1
                                    </c:if>
                                    <c:if test="${line.kodKategoriItemRampasan.kod eq 'B'}">
                                        ${line.kuantiti} ${line.kuantitiUnit}
                                    </c:if>
                                </display:column>
                                <display:column title="Tarikh Rampasan" class="${line_rowNum}">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhRampas}"/> <br>
                                </display:column>
                                <c:if test="${syorKompaunNS}">
                                    <display:column title="Syor Kompaun (RM)">
                                        <s:text name="senaraiBarangRampasan[${line_rowNum-1}].amaunKompaunSyor" formatPattern="0.00" id="amountSyor${line_rowNum-1}" size="10" maxlength="14" onblur="totalAmount();" onkeyup="validateNumber(this,this.value);"/>
                                    </display:column>
                                    <display:footer>
                                        <tr>
                                            <td colspan="5" align="right">Jumlah Syor(RM):</td>
                                            <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="10"
                                                       class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                        <tr>
                                        </display:footer>
                                    </c:if>
                                    <c:if test="${muktamadKompaunMLK}">
                                        <display:column title="Muktamad Kompaun (RM)">
                                            <s:text name="amaunMuktamadKompaun${line_rowNum-1}" class="number" formatPattern="0.00" id="amountSyor${line_rowNum-1}" size="10" maxlength="14" onblur="totalAmount();" onkeyup="validateNumber(this,this.value);"/>
                                        </display:column>
                                        <display:footer>
                                        <tr>
                                            <c:if test="${actionBean.idIP}">
                                                <td colspan="6" align="right">Jumlah(RM):</td>
                                            </c:if>
                                            <c:if test="${!actionBean.idIP}">
                                                <td colspan="6" align="right">Jumlah Syor(RM):</td>
                                            </c:if>

                                            <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="10"
                                                       class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                        <tr>
                                        </display:footer>
                                    </c:if>
                                    <c:if test="${bayaranPelupusan}">
                                        <display:column title="Muktamad Bayaran Pelupusan (RM)">
                                            <s:text name="amaunMuktamadKompaun${line_rowNum-1}" id="amountSyor${line_rowNum-1}" size="10" maxlength="14" onblur="totalAmount();" class="number" onkeyup="validateNumber(this,this.value);"/>
                                        </display:column>
                                        <display:footer>
                                        <tr>
                                            <td colspan="6" align="right">Jumlah Syor(RM):</td>
                                            <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="10"
                                                       class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                        <tr>
                                        </display:footer>
                                    </c:if>


                                </display:table>
                            </c:if>

                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <p align="right">
                    <c:if test="${syorKompaunNS}">
                        <s:button class="btn" name="simpanSyorKompaun" value="Simpan" onclick="if(validateForm())save(this.name,this.form);"/>
                    </c:if>
                    <c:if test="${muktamadKompaunMLK}">
                        <s:button class="btn" name="simpanMuktamadKompaunMelaka" value="Simpan" onclick="if(validateForm())save1(this.name,this.form);"/>
                    </c:if>
                    <c:if test="${bayaranPelupusan}">
                        <s:button class="btn" name="simpanBayaranPelupusan" value="Simpan" onclick="if(validateForm())save2(this.name,this.form);"/>
                    </c:if>
                    <c:if test="${multipleOp}">
                        <%-- <s:button class="btn" name="simpanMuktamadKompaunMultipleOp" value="Simpan" onclick="if(validateForm())save3(this.name,this.form);"/> --%>
                        <s:button class="btn" name="simpanMuktamadKompaunOks" value="Simpan" onclick="if(validateFormOKS())save3(this.name,this.form);"/>
                    </c:if>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    <s:hidden name="idOp"/>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>
