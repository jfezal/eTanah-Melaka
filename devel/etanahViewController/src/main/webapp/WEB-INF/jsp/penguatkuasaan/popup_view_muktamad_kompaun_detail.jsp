<%--
    Document   : popup_view_muktamad_kompaun_detail
    Created on : July 12, 2011, 11:52:22 AM
    Author     : latifah.iskak
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
    <c:if test="${multipleOp}">
            var bil =  1;
    </c:if>
    <c:if test="${!multipleOp}">
            var bil = ${actionBean.recordCount};
    </c:if>             
            for (var i = 0; i < bil; i++){
                var amount = document.getElementById('amountMuktamad'+i).value;
                if(amount !=""){
                    a += parseFloat(amount);
                    //document.getElementById('jumMuktamad').value=a;
                    document.getElementById('jumMuktamad').value=parseFloat(a).toFixed(2);

                }
            }

        });

        function totalMuktamad(){
            var total = 0;
            var bil = ${actionBean.recordCount};
            for (var i = 0; i < bil; i++){
                var amount = document.getElementById('amountMuktamad'+i).value;
                if(amount !=""){
                    total += parseFloat(amount);
    <%--alert("total : "+total);--%>
                    document.getElementById('jumMuktamad').value=total+".00";
                }
            }
        }

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

       

</script>

<s:form  beanclass="etanah.view.penguatkuasaan.MaklumatKompaunActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test="${!bayaranPelupusan}">
                    Maklumat Tawaran Kompaun
                </c:if>
                <c:if test="${bayaranPelupusan}">
                    Maklumat Muktamad Bayaran Pelupusan
                </c:if>
            </legend>
            <div class="content">
                <p>
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
                <p>
                <fieldset class="aras2">
                    <legend>
                        Barang Rampasan
                    </legend>
                    <div class="instr-fieldset">
                        <c:if test="${!bayaranPelupusan}">
                            Item barang rampasan yang akan dikenakan kompaun
                        </c:if>
                        <c:if test="${bayaranPelupusan}">
                            Item barang rampasan yang akan dikenakan muktamad bayaran pelupusan
                        </c:if>

                    </div>

                    <div align="center" >

                        <c:if test="${multipleOp}">
                            <display:table class="tablecloth" name="${actionBean.oks}" pagesize="10" cellpadding="0" cellspacing="0" id="line" style="width:90%;">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
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
                                    <fmt:formatNumber pattern="#,##0.00" value="${line.amaunKompaunSyor}"/>
                                    <input name="syor" value="${line.amaunKompaunSyor}" id="amountMuktamad${line_rowNum-1}" type="hidden"/>
                                </display:column>
                                <display:footer>
                                    <tr>
                                        <td colspan="3" align="right">Jumlah Syor(RM):</td>
                                        <td><input name="jumMuktamad" value="0.00" id="jumMuktamad" size="12" pattern="#,##0.00"
                                                   class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                    <tr>
                                    </display:footer>
                                </display:table>
                            </c:if>

                            <c:if test="${!multipleOp}">
                                <display:table class="tablecloth" name="${actionBean.listItemBarang}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
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
                                    <display:column title="Syor Kompaun (RM)" format="{0,number, 0.00}">
                                        ${line.amaunKompaunSyor}
                                        <input name="syor" value="${line.amaunKompaunSyor}" id="syorAmount${line_rowNum-1}" type="hidden"/>
                                    </display:column>
                                    <display:column title="Muktamad Kompaun (RM)">
                                        <fmt:formatNumber pattern="#,##0.00" value="${line.amaunKompaun}"/>
                                        <input name="syor" value="${line.amaunKompaun}" id="amountMuktamad${line_rowNum-1}" type="hidden"/>
                                    </display:column>
                                    <display:footer>
                                        <tr>
                                            <td colspan="5" align="right">Jumlah Syor(RM):</td>
                                            <td><input name="jumMuktamad" value="0.00" id="jumMuktamad" size="12"
                                                       class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                        <tr>
                                        </display:footer>
                                    </c:if>

                                    <c:if test="${muktamadKompaunMLK || multipleOp}">
                                        <display:column title="Muktamad Kompaun (RM)">
                                            <fmt:formatNumber pattern="#,##0.00" value="${line.amaunKompaun}"/>
                                        <input name="syor" value="${line.amaunKompaun}" id="amountMuktamad${line_rowNum-1}" type="hidden"/>
                                    </display:column>
                                    <display:footer>
                                        <tr>
                                            <td colspan="4" align="right">Jumlah Syor(RM):</td>
                                            <td><input name="jumMuktamad" value="0.00" id="jumMuktamad" size="12"
                                                       class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                        <tr>
                                        </display:footer>
                                    </c:if>

                                    <c:if test="${bayaranPelupusan}">
                                        <display:column title="Bayaran Pelupusan (RM)">
                                            ${line.amaunKompaun}
                                        <input name="syor" value="${line.amaunKompaun}" id="amountMuktamad${line_rowNum-1}" type="hidden"/>
                                    </display:column>
                                    <display:footer>
                                        <tr>
                                            <td colspan="4" align="right">Jumlah (RM):</td>
                                            <td><input name="jumMuktamad" value="0.00" id="jumMuktamad" size="12"
                                                       class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                                        <tr>
                                        </display:footer>
                                    </c:if>


                                </display:table>
                            </c:if>

                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <p align="center">
                    <s:hidden name="orangKenaSyak" id="orangKenaSyak"/>
                    <input name="idKompaun" value="${actionBean.kompaun.idKompaun}" type="hidden"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>


            </div>
        </fieldset>
    </div>
</s:form>