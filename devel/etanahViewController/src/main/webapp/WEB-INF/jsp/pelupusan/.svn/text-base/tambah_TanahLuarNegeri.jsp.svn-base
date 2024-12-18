<%-- 
    Document   : tambah_pemohonBatuan
    Created on : Friday June 10 2011, 11:28AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    
    $(document).ready( function(){
        maximizeWindow();
        <c:if test="${!flag}">
            opener.refreshMaklumatPemohon();
            self.close();
        </c:if>
          
             });
        
    

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
         
         
        function addSeterusnya(){
            window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showTambahLatarbelakangPemohon", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=710,height=400,scrollbars=yes");
        }


</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohon1ActionBean">

        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        

            <fieldset class="aras1">
                <legend>Maklumat Pemilikan Tanah Di Luar Negeri</legend>
                <br/>
                        
                        <font color ="red">Sila Masukkan Maklumat Yang Bertanda *</font>
                        <p>
                            <s:hidden name="idPemohon" id="idPemohon" value="${actionBean.pemohon.idPemohon}"/>&nbsp;
                        </p> 
                        <p>
                            <label for="noLot"><font color="red" >*</font>No Lot :</label>
                            <s:text name="pemohonTanah.noLot" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>

                        </p>
                         <p>
                            <label for="bpm"><font color="red" >*</font>Mukim :</label>
                            <s:text name="pemohonTanah.bandarPekanMukim" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label for="daerah"><font color="red" >*</font>Daerah :</label>
                            <s:text name="pemohonTanah.daerah" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label for="negeri"><font color="red" >*</font>Negeri :</label>
                            <s:select name="pemohonTanah.negeri.kod" style="width:246px">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod" />
                            </s:select>
                        </p>
                        <p>
                            <label for="jenisGeran">Jenis Geran :</label>
                            <s:text name="pemohonTanah.jenisGeran" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label for="luas">Luas :</label>
                            <s:text name="pemohonTanah.luas" size="40"  onkeyup="this.value=this.value.toUpperCase();"/>
                            <s:select name="luasUom.kod" value="${actionBean.pemohonTanah.luasUom.kod}" id="kULuas">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="H">Hektar</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                            </s:select>
                        </p>
                        <p>
                            <label for="usaha">Usaha Atas Tanah :</label>
                            <s:textarea name="pemohonTanah.pengusahaan" cols="40" rows="5" onkeyup="this.value=this.value.toUpperCase();"/>
                        </p>
           

                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="simpanPemohonTanah" id="simpan" value="Simpan" class="btn" />                        
                        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
            
                <br>
            </fieldset>
        
    </s:form>
</div>
