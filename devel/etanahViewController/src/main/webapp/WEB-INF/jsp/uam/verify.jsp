<%-- 
    Document   : verify
    Created on : Jul 5, 2011, 11:34:05 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE html>
<script type="text/javascript">
    function sah(id){
        //alert(id);
        if(confirm("Adakah Anda Pasti?")){
            //alert("save id "+id);
            $.post('${pageContext.request.contextPath}/uam/verify_user?sahPenyelia&idPguna='+id,
            function(data){
                if(data != ''){
                    //alert('Pelan untuk no lot '+ noLot +' tiada');                         
                    $('#mainWindow').html(data);
                    
                }
            }, 'html');
        }      
    }
    function viewDetails(frm, value) {
        var url = '${pageContext.request.contextPath}/uam/viewMohonPguna?getMohonPgunaData&idPengguna='+value;
        frm.action = url;
        frm.submit();
    }
    
    function sahAdmin(id,password){
        //alert(id);
        //alert(id);
        //alert(password);
        if(confirm("Adakah Anda Pasti?")){
            //alert("save id "+id);
            $.post('${pageContext.request.contextPath}/uam/verify_user?sahAdmin&idPguna='+id+'&password='+password,
            function(data){
                if(data != ''){
                    //alert('Pelan untuk no lot '+ noLot +' tiada');                         
                    $('#mainWindow').html(data);                   
                }
            }, 'html');
        }      
    }
    
    function showPwdForm(id){
        $('#showPasswordForm'+id).show();
    }
    
    function sendSah(type,fieldCounter,typeSTS){
        var tex = $('#test'+fieldCounter).val();
        var pgunatex = $('#pguna'+fieldCounter).val();

                var url = '${pageContext.request.contextPath}/uam/verify_user?sahPenyelia&idPguna='+pgunatex+'&status='+typeSTS+'&catatan='+tex;
                document.location.href =url; 
//                $.post(url,
//                function(data){
//                    $(self.document).html(data);
//                     },'html');
//                window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=910,height=800,scrollbars=yes");
    }

</script>
<%--<div id="mainWindow">
    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.uam.LupaKataLaluanBean">
        <div class="subtitle">
            <s:errors/>
            <s:messages/>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pengesahan Pengguna</legend>
                <center>
                    <display:table class="tablecloth" name="${actionBean.listMohonPguna}" style="width:60%;" cellpadding="0" cellspacing="0" id="linemohonpguna">
                        <display:column property="idPengguna"  title="ID Pengguna" />
                        <display:column property="nama"  title="Nama" />
                        <display:column property="status.nama"  title="Status" />
                        <display:column title="&nbsp;">
                            <c:if test="${actionBean.peng.perananUtama.kod ne '6'}">
                                <c:if test="${linemohonpguna.status.kod eq 'BR' || linemohonpguna.status.kod eq 'KM' }">
                                    <a href="?sahPenyelia&idPguna=${linemohonpguna.idPengguna}&status=SH" >Sahkan</a> / <a href="?sahPenyelia&idPguna=${linemohonpguna.idPengguna}&status=TK">Tolak</a>
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.peng.perananUtama.kod eq '6'}">
                                <c:if test="${linemohonpguna.status.kod eq 'SH'}">
                                    <a href="#" style="text-decoration: none;" onClick="showPwdForm('${linemohonpguna_rowNum}');">Sahkan
                                        <div id="showPasswordForm${linemohonpguna_rowNum}" style="display:none;">
                                            <p>
                                                 <label>&nbsp;</label>
                                                <input type="hidden" name="idPguna" value="${linemohonpguna.idPengguna}" >
                                                <s:submit name="sendingPassword" value="Sah" class="btn"/>
                                                <s:submit name="kembali1" value="Kembali" class="btn"/>
                                            </p>
                                        </div>
                                    </a>
                                </c:if>
                            </c:if>
                            &nbsp;
                        </display:column>
                    </display:table>
                </center>
                </p>
            </fieldset>
        </div>
    </s:form>
</div>--%>

            <%----or----%>
<div id="mainWindow">
    <s:form beanclass="etanah.view.uam.VerifyUserActionBean" name="verifyMohonPguna">
        <div class="subtitle">
            <s:errors/>
            <s:messages/>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Pengesahan Pengguna</legend>
                <center>
                    <c:set value="0" var="i"/>
                    <display:table class="tablecloth" name="${actionBean.listMohonPguna}" style="width:60%;" cellpadding="0" cellspacing="0" id="linemohonpguna">
                        
                        <display:column title="Bil">
                            ${i+1}<s:hidden id="pguna${i}" name="pguna${i}" value="${linemohonpguna.idPengguna}"/>
                        </display:column>
                        <display:column property="idPengguna"  title="ID Pengguna"  />
                        <display:column property="nama"  title="Nama" />
                        <display:column title="Tindakan">
                                <div align ="center">
                                   <img alt='Klik Untuk Papar Butiran' border='0' src='${pageContext.request.contextPath}/images/view_icon.gif' class='rem'
                                        id='rem_${line_rowNum}' onclick="viewDetails(document.forms.verifyMohonPguna,'${linemohonpguna.idPengguna}')" onmouseover="this.style.cursor='pointer';"></img>
                                </div>
                                </display:column>
                        <display:column property="status.nama"  title="Status" />

                        <c:set var="i" value="${i+1}" />
                    </display:table>
                </center>
                </p>
            </fieldset>
        </div>
    </s:form>
</div>