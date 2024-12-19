<%-- 
    Document   : popup_load
    Created on : Jun 7, 2013, 3:15:15 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var kod = $('#dokumenKod').val();
        var idRuj = $('#idRujukan').val();
        if(kod == 'IH'){
            self.opener.refreshListHakmilik();
        }else if(kod == 'KS'){
            self.opener.refreshUploadLampiran();
        }else if(kod == 'LO'){
            self.opener.refreshPage();
        }else if(kod == 'LP'){
            self.opener.refreshPagePolis();
        }else if(kod == 'IB'){
            self.opener.refreshPage();
        }else if(kod == 'BR'){
            self.opener.refreshPage();
        }else if(kod == 'RP'){
            self.opener.refreshUploadRP();
        }else if(kod == 'SSPK'){
            self.opener.refreshPageKompaun();
        }else if(kod == 'KMD'){
            self.opener.refreshPageMahkamah();
        }else if(idRuj == 'DokumenDisemak'){
            self.opener.refreshPageSemakDokumen($('#idLaporan').val());
        }else if(idRuj != null){
            if(kod == 'IHU'|| kod == 'IHS' || kod == 'IHT' || kod == 'IHB'){
                self.opener.refreshListSempadan();
            }
        }else if(idRuj == null){
            if(kod == 'IHU'|| kod == 'IHS' || kod == 'IHT' || kod == 'IHB'){
                self.opener.refreshTanahSekeliling(kod);
            }
        }
    });
    function simpanDoc(){
    <%--alert($('#dokumenKod').val());--%>
            var kod = $('#dokumenKod').val();
            var idRuj = $('#idRujukan').val();
            if(kod == 'IH'){
                self.opener.refreshListHakmilik();
            }else if(kod == 'KS'){
                self.opener.refreshUploadLampiran();
            }else if(kod == 'LO'){
                self.opener.refreshPage();
            }else if(kod == 'LP'){
                self.opener.refreshPagePolis();
            }else if(kod == 'IB'){
                self.opener.refreshPage();
            }else if(kod == 'BR'){
                self.opener.refreshPage();
            }else if(kod == 'RP'){
                self.opener.refreshUploadRP();
            }else if(kod == 'SSPK'){
                self.opener.refreshPageKompaun();
            }else if(kod == 'KMD'){
                self.opener.refreshPageMahkamah();
            }else if(idRuj == 'DokumenDisemak'){
                self.opener.refreshPageSemakDokumen($('#idLaporan').val());
            }else if(idRuj != null){
                if(kod == 'IHU'|| kod == 'IHS' || kod == 'IHT' || kod == 'IHB'){
                    self.opener.refreshListSempadan();
                }
            }else if(idRuj == null){
                if(kod == 'IHU'|| kod == 'IHS' || kod == 'IHT' || kod == 'IHB'){
                    self.opener.refreshTanahSekeliling(kod);
                }
            }
        }


        function simpan(){
            var idRuj = $('#idRujukan').val();
            if(idRuj == 'DokumenDisemak'){
                self.opener.refreshPageDokumenDisemak($('#idLaporan').val());
            }
        }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.UploadActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="folderId"/>
    <s:hidden id="idPermohonan" name="idPermohonan"/>
    <s:hidden id="dokumenKod" name="dokumenKod"/>
    <s:hidden id="idLaporan" name="idLaporan"/>
    <s:hidden id="idHakmilik" name="idHakmilik"/>
    <s:hidden id="idRujukan" name="idRujukan"/>
    <s:hidden id="idBarang" name="idBarang"/>
    <s:hidden id="idDokumen" name="idDokumen"/>
    <s:hidden id="idKertas" name="idKertas"/>

    <fieldset class="aras1">
        <legend>Muat Naik</legend>
        <p>
            <label> Fail :</label>&nbsp;
            <s:file id="file" name="fileToBeUpload"/>

        </p>
        <c:if test="${actionBean.dokumenKod ne 'IB'}">
            <p>
                <label> Catatan :</label>
                &nbsp;&nbsp;<s:textarea id="catatan" name="catatan" cols="30" rows="2" />
            </p>
        </c:if>
        <p>
            <label>&nbsp;</label>&nbsp;
            <c:if test="${actionBean.idRujukan ne 'DokumenDisemak'}">
                <s:submit name="processUploadDoc" value="Simpan" class="btn" onclick="simpanDoc();"/>
            </c:if>

            <c:if test="${actionBean.idRujukan eq 'DokumenDisemak'}">
                <s:submit name="processUploadDoc" value="Simpan" class="btn" onclick="simpan();"/>
            </c:if>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </fieldset>
</s:form>
