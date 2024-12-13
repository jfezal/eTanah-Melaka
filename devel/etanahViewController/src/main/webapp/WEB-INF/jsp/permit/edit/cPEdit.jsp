<%-- 
    Document   : carianPihak
    Created on : Sep 16, 2015, 2:37:23 PM
    Author     : Hazwan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
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
    $(document).ready(function(){
        $('#page_effect').fadeIn(2000);
    });
    
    function cPihak(id,jb,nop){             
        var url = '${pageContext.request.contextPath}/digiSignPermit?cariBayaran&noFail='+id+'&jenisBorang='+jb+'&noPermit='+nop;         
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function savingPihak(id,jb,nop){       
        var url = '${pageContext.request.contextPath}/digiSignPermit?simpanCarianPihak&noFail='+id+'&jenisBorang='+jb+'&noPermit='+nop;         
        $.get(url,
        function(data){
            $('#page_div').html(data);
                
            self.opener.refreshPihak($('#nama').val(),$('#alamat1').val(),
            $('#alamat2').val(),$('#alamat3').val(),
            $('#alamat4').val(),$('#poskod').val(),
            $('#negeri').val(),$('#noPengenalan').val());
        },'html');     
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:messages/>
<div class="subtitle" id="page_effect" style="display:none;">
    <s:form beanclass="etanah.view.stripes.permit.DSPermitActionBean" >       
        <s:hidden name="noFail" id="noFail"/>
        <s:hidden name="jenisBorang" id="jenisBorang" />
        <s:hidden name="noPermit" id="noPermit" />

        <fieldset class="aras1">
            <legend>
                Carian Pemegang Permit
            </legend>
            <div align="center">
                <p>
                    <b>No. Pengenalan :</b>
                    <s:text name="noPengenalan" size="20" id="noPengenalan" class="normal_text"/>
                </p>

                <p>
                    <s:submit name="cariPemilikNoIc" value="cari" class="btn" onclick="cPihak('${actionBean.noFail}','${actionBean.jenisBorang}','${actionBean.noPermit}')"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>       
                </p>
            </div>
            <br>
        </fieldset>
        <br/>         
        <c:if test="${find}">
            <fieldset class="aras1">
                <legend>
                    Hasil Carian Pemegang Permit
                </legend>
                <c:if test="${found}">
                    <div class="content" align="center">
                        <table border="0" width="50%">
                            <c:if test="${actionBean.nama != null}">
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Pengenalan </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                    <td><s:text name="noPengenalan" size="30" id="noPengenalan" class="normal_text" readonly="true"/></td>
                                </tr> 
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Nama </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                    <td><s:text name="nama" size="30" id="nama" class="normal_text" onblur="this.value=this.value.toUpperCase();"/></td>
                                </tr> 
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Alamat 1 </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                    <td><s:text name="alamat1" size="30" id="alamat1" class="normal_text" onblur="this.value=this.value.toUpperCase();"/></td>
                                </tr>
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Alamat 2 </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                    <td><s:text name="alamat2" size="30" id="alamat2" class="normal_text" onblur="this.value=this.value.toUpperCase();"/></td>
                                </tr>
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Alamat 3 </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                    <td><s:text name="alamat3" size="30" id="alamat3" class="normal_text" onblur="this.value=this.value.toUpperCase();"/></td>
                                </tr>
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Alamat 4 </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                    <td><s:text name="alamat4" size="30" id="alamat4" class="normal_text" onblur="this.value=this.value.toUpperCase();"/></td>
                                </tr>
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Poskod </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                    <td><s:text name="poskod" size="30" maxlength="5" id="poskod" class="normal_text"/></td>
                                </tr>                                
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Negeri </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                    <td>
                                        <s:select name="negeri" id="negeri" style="width:210px;" >
                                            <s:option value="">Pilih</s:option>
                                            <s:options-collection collection="${listUtil.senaraiKodNegeriAktif}" label="nama" value="kod" />
                                        </s:select>
                                    </td>
                                </tr>
                            </c:if>                               
                        </table>
                    </div>

                    <p>
                        <c:if test="${actionBean.nama != null}">
                        <div align="center">
                            <s:submit name="simpanCarianPihak" value="Simpan" class="btn" onclick="savingPihak('${actionBean.noFail}','${actionBean.jenisBorang}','${actionBean.noPermit}')"/>
                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        </div>
                    </c:if>
                    </p>

                    <p>                 
                        <c:if test="${actionBean.nama == null}">
                        <div align="center"><em>Maklumat Pengenalan Tidak Wujud Di Dalam Pangkalan Data Etanah</em></div>
                    </c:if>
                    </p>  
                </c:if>
            </fieldset>
        </c:if>
    </s:form>
</div>
