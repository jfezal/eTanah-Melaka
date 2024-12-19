<%-- 
    Document   : tambahRekod
    Created on : Jan 30, 2012, 10:04:33 PM
    Author     : Aziz
--%>

<%-- 
    Document   : updateKodUrusanAgensi
    Created on : Jan 21, 2012, 12:48:53 AM
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
            var url = '${pageContext.request.contextPath}/utility_kod/kodUrusanAgensi?simpanData&table_name='+value;
            frm.action = url;
            frm.submit();
            }
        }
         function goTo2(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodUrusanAgensi?KodList&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodUrusAgensiActionBean" name ="tambahKodUrusanAgensi" id ="tambahKodUrusanAgensi">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Tambah Kod Urusan Agensi</legend>
                <p><label><font color="red">*</font>Urusan:</label>
                         <s:select name="urusan" id="urusan" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodUrusan}" label="nama" value="kod" />
                    </s:select>
                </p>
                <s:hidden value = "${actionBean.nameTable}" name = "namatable"/>
                     <s:hidden value = "${actionBean.kod}" name = "kod"/>
                <p><label><font color="red">*</font>Kod Agensi</label>
                         <s:select name="agensi" id="agensi">
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodAgensi}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p><label><font color="red">*</font>Aktif:</label>
                         <s:select name="aktif" id="aktif" >
                             <s:option value="Y">Aktif</s:option>
                                  <s:option value="T">Tidak</s:option>
                        </s:select>
                 </p>
                 <br/><br/><br/>
                 <p align="center"><s:submit name="simpan" onclick="goTo(document.forms.tambahKodUrusanAgensi,'${actionBean.nameTable}')" value="Simpan" class="btn"/>&nbsp;<s:submit name = "kembali" onclick="goTo2(document.forms.tambahKodUrusanAgensi,'${actionBean.nameTable}')" class="btn" value ="Kembali"/></p>
         
            </fieldset>
        </div>
       
    </s:form>
</div>



