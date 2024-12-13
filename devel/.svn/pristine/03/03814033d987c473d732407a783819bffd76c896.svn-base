<%--
    Document   : tawaran_kompaun_muktamad_oks
    Created on : July 21, 2011, 3:09:22 PM
    Author     : sitifariza.hanim
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript">
    $(document).ready(function() {
        var a = 0.00;
        var bil = ${actionBean.rowCount};
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('amountMuktamad'+i).value;
            if(amount !=""){
                a += parseFloat(amount);
               
            }
        }
        if (a != null){
                    document.getElementById('jumCaraBayar').value=(parseFloat(a).toFixed(2));;
                }
    });
    function refreshPageKompaunOKS(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/oks_maklumat_kompaun?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function viewMuktamadKompaun(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/oks_maklumat_kompaun?viewMuktamadKompaunDetail&idKompaun='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function totalAmount(){
        var total = 0;
        var bil = ${actionBean.rowCount};
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('amountMuktamad'+i).value;
            if(amount !=""){
                total += parseFloat(amount);
                document.getElementById('jumCaraBayar').value=total;
            }
        }
    }

    function validateForm(){

        var bil =  ${fn:length(actionBean.senaraiKompaun)};
        for (var i = 0; i < bil; i++){

            var syorAmount = document.getElementById('syorAmount'+i).value;
            var muktamadAmount = document.getElementById('amountMuktamad'+i).value;

            if(parseFloat(muktamadAmount) > parseFloat(syorAmount)){
                alert("Nilai Muktamad Kompaun mestilah lebih kecil dari Syor Kompaun");
                $('#amountMuktamad'+i).focus();
                return false;
            }

            if(muktamadAmount == ""){
                alert("Sila masukkan nilai muktamad kompaun");
                $('#muktamadAmount'+i).focus();
                return false;

            }


        }

        return true;
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
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

</script>
<s:form beanclass="etanah.view.penguatkuasaan.OKSMaklumatKompaunActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Muktamad Kompaun
            </legend>

            <div class="content" align="center">
                <fieldset class="aras2">
                <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true">
                        ${line_rowNum}
                    </display:column>
                    <display:column title="Nama">
                        ${line.isuKepada}
                    </display:column>
                    <display:column title="No.KP">${line.noPengenalan}</display:column>
                    <display:column title="Syor Kompaun (RM)">
                        <fmt:formatNumber pattern="0.00" value="${line.orangKenaSyak.amaunKompaunSyor}"/>&nbsp;
                        <input name="syor" value="${line.orangKenaSyak.amaunKompaunSyor}" id="syorAmount${line_rowNum-1}" type="hidden"/>
                    </display:column>
                    <display:column title="Tempoh (Hari)">
                        <c:if test="${line.tempohHari eq 0}">
                            <s:text name="tempohHari${line_rowNum-1}" value ="14" id="tempohHari${line_rowNum-1}" size="5" onkeyup="validateNumber(this,this.value);" />
                        </c:if>
                        <c:if test="${line.tempohHari ne 0}">
                            <s:text name="tempohHari${line_rowNum-1}" value ="${line.tempohHari}" id="tempohHari${line_rowNum-1}" size="5" onkeyup="validateNumber(this,this.value);" />
                        </c:if>
                    </display:column>
                    <display:column title="Muktamad Kompaun(RM)">
                        <s:text name="muktamadKompaun${line_rowNum-1}" value ="${line.amaun}" formatPattern="###0.00" id="amountMuktamad${line_rowNum-1}" size="20" maxlength="14" onblur="totalAmount();" onkeyup="validateNumber(this,this.value);" />
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="5" align="right">Jumlah Syor(RM):</td>
                            <td><s:text name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" formatPattern="###0.00"
                                    class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                        <tr>
                        </display:footer>

                    </display:table>
                <br/>

                <c:if test="${fn:length(actionBean.senaraiKompaun) ne 0}">
                    <p align="center">
                        <s:button class="btn" name="simpanMuktamadKompaun" value="Simpan" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                    </p>
                </c:if>


                <br>
                </fieldset>

            </div>
        </fieldset>
    </div>
</s:form>
