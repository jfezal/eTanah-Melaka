<%-- 
    Document   : tambahRekod
    Created on : Jan 30, 2012, 10:04:33 PM
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
            var url = '${pageContext.request.contextPath}/utility_kod/kodAkaun?simpanData&table_name='+value;
            frm.action = url;
            frm.submit();
            }
        }
         function goTo2(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodAkaun?KodList&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodAkaunActionBean" name ="tambahKodAkaun" id ="tambahKodAkaun">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Tambah Kod Akaun</legend>
                <p><label><font color="red">*</font>Kod Akaun : </label><s:text name ="kodAkaun" id="kodAkaun" value = "${actionBean.kodAkaun}"/>       
                </p>
                <p><label><font color="red">*</font>Kod Kategori Akaun :</label>
                         <s:select name="katgAkaun" id="katgAkaun" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodKategoriAkaun}" label="nama" value="kod" />
                    </s:select>
                </p>
                <s:hidden value = "${actionBean.id_rekod_akaun}" name = "id_rekod_akaun"/>
                 <p><label><font color="red">*</font>Nama : </label><s:text name ="nama" id="nama" value = "${actionBean.nama}"/>
                        
                </p>
                <s:hidden value = "${actionBean.nameTable}" name = "namatable"/>
                     <s:hidden value = "${actionBean.kod}" name = "kod"/>
               <p>
                        <label>Aktif  :</label>
                        <s:radio name="aktif" value="Y"></s:radio> Aktif
                        <s:radio name="aktif" value="N" ></s:radio> Tidak
                    </p>
              
                 <br/><br/><br/>
                 <p align="center"><s:submit name="simpan" onclick="goTo(document.forms.tambahKodAkaun,'${actionBean.nameTable}')" value="Simpan" class="btn"/>&nbsp;<s:submit name = "kembali" onclick="goTo2(document.forms.tambahKodAkaun,'${actionBean.nameTable}')" class="btn" value ="Kembali"/></p>
         
            </fieldset>
        </div>
       
    </s:form>
</div>



