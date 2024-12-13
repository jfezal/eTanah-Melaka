<%-- 
    Document   : tambahRekodAgensi
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
         function goTo(frm, value) {
             if(confirm("Adakah anda pasti untuk simpan data?")){
            var url = '${pageContext.request.contextPath}/utility_kod/kodDokumenAgensi?simpanData&table_name='+value;
            frm.action = url;
            frm.submit();
            }
        }
         function goTo2(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodDokumenAgensi?KodList&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodDokumenAgensiActionBean" name ="tambahKodDokumenAgensi" id ="tambahKodDokumenAgensi">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Tambah Kod Dokumen Agensi </legend>
                <p><label><font color="red">*</font>Kod Urusan Agensi</label>
                         <s:select name="kodUrusanAgensi" id="kodUrusanAgensi" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUrusanAgensi}" label="idKodUrusanAgensi" value="idKodUrusanAgensi" />
                    </s:select>
                </p>
                <s:hidden value = "${actionBean.id_rekod_dokumen_agensi}" name = "id_rekod_dokumen_agensi"/>
                
                 <p><label><font color="red">*</font>Kod Dokumen:</label>
                         <s:select name="kodDokumen" id="kodDokumen" >
                                <s:option value="00">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDokumen}" label="nama" value="kod" />
                    </s:select>
                </p>
                 
                <s:hidden value = "${actionBean.nameTable}" name = "namatable"/>
                <s:hidden value = "${actionBean.kod}" name = "kod"/>
                
                 <p>
                        <label>Aktif  :</label>
                        <s:radio name="aktif" value="Y"></s:radio> Aktif
                        <s:radio name="aktif" value="T" ></s:radio> Tidak
                    </p>
              
                 <br/><br/><br/>
                <p align="center"><s:submit name="simpan" onclick="goTo(document.forms.tambahKodDokumenAgensi,'${actionBean.nameTable}')" value="Simpan" class="btn"/>&nbsp;<s:submit name = "kembali" onclick="goTo2(document.forms.tambahKodDokumenAgensi,'${actionBean.nameTable}')" class="btn" value ="Kembali"/></p>
         
            </fieldset>
        </div>
       
    </s:form>
</div>



