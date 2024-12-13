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
            var url = '${pageContext.request.contextPath}/utility_kod/kodBPM?simpanData&table_name='+value;
            frm.action = url;
            frm.submit();
            }
        }
         function goTo2(frm, value) {
            var url = '${pageContext.request.contextPath}/utility_kod/kodBPM?KodList&table_name='+value;
            frm.action = url;
            frm.submit();
        }
       
       
        
    </script>
    <s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.utility.kod.KodBPMActionBean" name ="tambahKodBPM" id ="tambahKodBPM">
        <s:messages/>
        <s:errors/>
        <div class ="subtitle displaytag">
            <fieldset class ="aras1">
                <legend>Tambah Kod Bandar Pekan Mukim</legend>
                <p><label><font color="red">*</font>Daerah :</label>
                         <s:select name="daerah" id="daerah" >
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p><label><font color="red">*</font>Kod BPM : </label><s:text name ="bpm" id="bpm" value = "${actionBean.bpm}"/>
                        
                </p>
                 <p><label><font color="red">*</font>Nama : </label><s:text name ="bpm" id="bpm" value = "${actionBean.nama}"/>
                        
                </p>
                <s:hidden value = "${actionBean.nameTable}" name = "namatable"/>
                     <s:hidden value = "${actionBean.kod}" name = "kod"/>
                <p><label><font color="red">*</font>Kod Tanah : </label>
                         <s:select name="tanah" id="tanah">
                                <s:option value="0">Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodTanah}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p><label><font color="red">*</font>Aktif : </label>
                         <s:select name="aktif" id="aktif" >
                             <s:option value='Y'>Aktif</s:option>
                                  <s:option value='T'>Tidak</s:option>
                        </s:select>
                 </p>
                 <br/><br/><br/>
                 <p align="center"><s:submit name="simpan" onclick="goTo(document.forms.tambahKodBPM,'${actionBean.nameTable}')" value="Simpan" class="btn"/>&nbsp;<s:submit name = "kembali" onclick="goTo2(document.forms.tambahKodBPM,'${actionBean.nameTable}')" class="btn" value ="Kembali"/></p>
         
            </fieldset>
        </div>
       
    </s:form>
</div>



