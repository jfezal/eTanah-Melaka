<%-- 
    Document   : kemaskiniKodBPM
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
           var url = '${pageContext.request.contextPath}/utility_kod/kodAliran?updateData&table_kod='+value+'&kod='+value2;
            frm.action = url;
            frm.submit();
            }
        }
         function goTo2(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodAliran?KodList&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodAliranActionBean" name ="kemaskiniKodAliran" id ="kemaskiniKodAliran">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Kemaskini Kod Aliran </legend>
                 <p><label><font color="red">*</font>Id Rekod    :</label>${actionBean.details.kod}
                </p>
                <p><label><font color="red">*</font>Kod Urusan:</label>
                         <s:select name="details.kodUrusan.kod" id="urusan" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUrusan}" label="nama" value="kod" />
                    </s:select>
                </p>
                <s:hidden value = "${actionBean.nameTable}" name = "namatable"/>
                <s:hidden value = "${actionBean.kod}" name = "kod"/>
                 
                <p><label><font color="red">*</font>Id Aliran :</label>
                            <s:text id="nama" name="details.idAliran"></s:text>
                </p>
                 <p><label><font color="red">*</font>Nama :</label>
                            <s:text id="nama" name="details.nama"></s:text>
                </p>
               
               <p>
                        <label>Aktif  :</label>
                        <s:radio name="details.aktif" value="Y"></s:radio> Aktif
                        <s:radio name="details.aktif" value="N" ></s:radio> Tidak
                    </p>
               <p><label><font color="red">*</font>Dimasukkan Oleh    :</label>${actionBean.details.infoAudit.dimasukOleh.idPengguna}
                </p>
                   <p><label><font color="red">*</font>Tarikh Kemasukan    :</label>${actionBean.details.infoAudit.tarikhMasuk}
                </p>
                <p><label><font color="red">*</font>DiKemaskini Oleh    :</label>${actionBean.details.infoAudit.dikemaskiniOleh.idPengguna}
                </p>
                   <p align="left"><label><font color="red">*</font>Tarikh Kemaskini    :</label>${actionBean.details.infoAudit.tarikhKemaskini}
                </p>
                 <br/><br/><br/>
                 <p align="center"><s:submit name="updateData" onclick="goTo(document.forms.kemaskiniKodAliran,'${actionBean.nameTable}','${actionBean.kod}')" value="Simpan" class="btn"/>&nbsp;<s:button name ="kembali" class="btn" value ="Kembali" onclick="goTo2(document.forms.kemaskiniKodAliran,'${actionBean.nameTable}')"/></p>
         
            </fieldset>
        </div>
       
    </s:form>
</div>


