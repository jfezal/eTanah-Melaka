<%--
    Document   : popup_view_muktamad_kompaun_oks
    Created on : July 24, 2011, 3:45:22 PM
    Author     : sitifariza.hanim
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var a = 0.00;
            var amount = document.getElementById('amountMuktamad').value;
            if(amount !=""){
                a += parseFloat(amount);
                document.getElementById('jumMuktamad').value=a;
        }

    });

    function totalMuktamad(){
        var total = 0;
            var amount = document.getElementById('amountMuktamad').value;
            if(amount !=""){
                total += parseFloat(amount);
    <%--alert("total : "+total);--%>
                    document.getElementById('jumMuktamad').value=total+".00";
   
            }
        }

        function save(event, f){

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.opener.refreshPageKompaunOKS();
                self.close();
            },'html');

        }

        function test(f) {
            $(f).clearForm();
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

        function validateForm(){
            
                var syorAmount = document.getElementById('syorAmount').value;
                var muktamadAmount = document.getElementById('amountMuktamad').value;

                if(parseFloat(muktamadAmount) > parseFloat(syorAmount)){
                    alert("Nilai Muktamad Kompaun mestilah lebih kecil dari Syor Kompaun");
                    $('#amountMuktamad').focus();
                    return false;
                }

                if(muktamadAmount == ""){
                    alert("Sila masukkan nilai muktamad kompaun");
                    $('#muktamadAmount').focus();
                    return false;

                }

            return true;
        }

</script>

<s:form  beanclass="etanah.view.penguatkuasaan.OKSMaklumatKompaunActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tawaran Kompaun
            </legend>
            <s:hidden name = "idKompaun"/>
            <div class="content">
       <%--         <p>
                    <label>No.Siri Kompaun :</label>
                    ${actionBean.kompaun.noRujukan}&nbsp;
                </p>
                <p>
                    <label>Tempoh (Hari) :</label>
                    ${actionBean.kompaun.tempohHari}&nbsp;
                </p>
                <p>
                    <label>Isu Kepada :</label>
                    ${actionBean.kompaun.isuKepada}&nbsp;
                </p>
                <p>--%>
                <fieldset class="aras2">
                    <legend>
                        Maklumat Tawaran Kompaun
                    </legend>
                    <div class="instr-fieldset">
                        Maklumat orang disyaki yang akan dikenakan kompaun
                    </div>

                    <div align="center" >

                        <display:table class="tablecloth" name="${actionBean.kompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Nama"><a class="popup" onclick="popup(${line.orangKenaSyak.nama})">${line.orangKenaSyak.nama}</a></display:column>
                            <display:column title="No.KP">${line.noPengenalan}</display:column>
                            <display:column title="Syor Kompaun (RM)" format="{0,number, 0.00}">
                                ${line.orangKenaSyak.amaunKompaunSyor}
                                <input name="syor" value="${line.orangKenaSyak.amaunKompaunSyor}" id="syorAmount" type="hidden"/>
                            </display:column>
                            <display:column title="Muktamad Kompaun (RM)" >
                                <s:text name="amaun" id="amountMuktamad" value="${line.amaun}" size="10" maxlength="14" onblur="totalMuktamad();" onkeyup="validateNumber(this,this.value);"/>
                            </display:column>
                            <%--<display:footer>
                                <tr>
                                    <td colspan="5" align="right">Jumlah Syor(RM):</td>
                                    <td><input name="jumMuktamad" value="0.00" id="jumMuktamad" size="12"
                                               class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                <tr>
                                </display:footer>--%>

                            </display:table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <p align="center">
                    <s:hidden name="orangKenaSyak" id="orangKenaSyak"/>
                    <input name="idKompaun" value="${actionBean.kompaun.idKompaun}" type="hidden"/>
                    <s:button class="btn" name="simpanMuktamadKompaun" value="Simpan" onclick="if(validateForm())save(this.name,this.form);"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>