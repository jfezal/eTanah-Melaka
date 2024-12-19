<%-- 
    Document   : kemaskiniKodAgensi
    Created on : Jan 13, 2012, 6:28:45 PM
    Author     : Aziz
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<div class= "all">
    <script type="text/javascript">
        maximizeWindow();
        //     $(document).ready(function(){
        //        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        //    });

        
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
        function goTo(frm, value,value2) {
            if(confirm("Adakah anda pasti untuk kemaskini data?")){
                var url = '${pageContext.request.contextPath}/utility_kod/kodDUN?updateData&table_name='+value+'&kod='+value2;
                frm.action = url;
                frm.submit();
            }
        }
        function goTo2(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodDUN?KodList&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodDUNActionBean" name ="kemaskiniKodDUN" id ="kemaskiniKodDUN">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Kemaskini Kod DUN </legend>
                <p><label><font color="red">*</font>Id Rekod    :</label>${actionBean.details.kod}
                </p>
                <p><label><font color="red">*</font>Nama :</label>
                        <s:text id="nama" name="details.nama" value ="${actionBean.details.nama}" size="50" onkeyup="this.value=this.value.toUpperCase();"></s:text>
                </p>
                <p><label><font color="red">*</font>Kawasan Parlimen :</label>
                        <s:select name="details.kodKawasanParlimen.kod" id="parlimen" >
                            <s:option value="0">Pilih</s:option>
                        <s:options-collection collection="${list.senaraiKodKawasanparlimen}" label="nama" value="kod" />
                    </s:select>
                </p>
                <c:if test="${actionBean.kodNegeri eq '04'}">
                    <p><label><font color="red">*</font>ADUN:</label>
                            <s:select name="details.kodAgensi.kod" id="agensi" >
                                <s:option value="00">Pilih</s:option>
                            <s:options-collection collection="${actionBean.listAdun}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </c:if>

                <s:hidden value = "${actionBean.nameTable}" name = "namatable"/>
                <s:hidden value = "${actionBean.kod}" name = "kod"/>

                <p>
                    <label>Aktif  :</label>
                    <s:radio name="aktif" value="Y"></s:radio> Aktif
                    <s:radio name="aktif" value="T" ></s:radio> Tidak
                    </p>
                    <p><label><font color="red">*</font>Dimasukkan Oleh    :</label>${actionBean.details.infoAudit.dimasukOleh.idPengguna}
                </p>
                <p><label><font color="red">*</font>Tarikh Kemasukan    :</label><s:format value="${actionBean.details.infoAudit.tarikhMasuk}" formatPattern="dd/MM/yyyy"/>
                </p>
                <p><label><font color="red">*</font>DiKemaskini Oleh    :</label>${actionBean.details.infoAudit.dikemaskiniOleh.idPengguna}
                </p>
                <p align="left"><label><font color="red">*</font>Tarikh Kemaskini    :</label><s:format value="${actionBean.details.infoAudit.tarikhKemaskini}" formatPattern="dd/MM/yyyy"/>
                </p>
                <br/><br/><br/>
                <p align="center"><s:submit name="updateData" onclick="goTo(document.forms.kemaskiniKodDUN,'${actionBean.nameTable}','${actionBean.kod}')" value="Simpan" class="btn"/>&nbsp;<s:button name ="kembali" class="btn" value ="Kembali" onclick="goTo2(document.forms.kemaskiniKodDUN,'${actionBean.nameTable}')"/></p>

            </fieldset>
        </div>

    </s:form>
</div>



