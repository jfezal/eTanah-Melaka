<%-- 
    Document   : carian_kelompok
    Created on : Apr 4, 2013, 10:09:54 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function openKelompok(){
        //        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahKelompok", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    
    function openBertindih(){
        //        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahBertindih", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    function editKelompok(idMohon,f){
        doBlockUI();
        var q = $(f).serialize();
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon='+idMohon;
        window.location = url+q;
        doUnBlockUI();
    }
    
    function editKelompok1(idMohon, frm) {
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon='+idMohon;
        frm.action = url;
        frm.submit();
    }
    
    function deleteKelompok(idMohon, frm) {
        if(confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?deleteKelompok&idMohon='+idMohon;
            frm.action = url;
            frm.submit();   
        }
    }
    
    function refreshKelompok(){
        var frm = document.forms.utilitiKelompok ;
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?findKelompok';
        frm.action = url;
        frm.submit();
    }
    
    function tambahPermohonan(){
        //        doBlockUI();
        var idKelompok = $('#idKelompok').val() ;
        window.open("${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?tambahPermohonan&idKelompok="+idKelompok, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    
    function popup(url)
    {
        params  = 'width=900';
        params += ', height=900';
        params += ', top=200, left=100';
        params += ', directories=no';
        params += ', fullscreen=no';
        params += ', location=no';
        params += ', menubar=yes';
        params += ', resizable=yes';
        params += ', scrollbars=yes';
        params += ', status=yes';
        params += ', toolbar=yes';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }
    
    function refreshPermohonanKelompok(val){
        //        alert(val);
        var frm = document.forms.utilitiKelompok ;
        var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?editKelompok&idMohon='+val;
        frm.action = url;
        frm.submit();
    }
    
    function selectCheckBox()
    {
        var e= $('#sizePermohonan').val();
        var idKelompok = $('#idKelompok').val() ;
        var cnt=0;
        var data = new Array() ;
        for(cnt=0;cnt<e;cnt++)
        {
            if(e=='1'){
                if(document.utilitiKelompok.checkmate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.utilitiKelompok.checkmate.value ;
                }

            }
            else {
                if(document.utilitiKelompok.checkmate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.utilitiKelompok.checkmate[cnt].value ;
                }
                else{
                    data[cnt] = "T" ;
                }
            }
        }
        //                    alert(data) ;
        if(confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?deletePermohonan&item='+data ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.refreshPermohonanKelompok(idKelompok);
            },'html');
        }
    }
    
    function janaPermohonan(){
        var idKelompok = $('#idKelompok').val() ;
        if(confirm("Adakah anda pasti untuk jana permohonan?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok?janaPermohonan&idKelompok='+idKelompok ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.refreshPermohonanKelompok(idKelompok);
            },'html');
        }
    }
    

</script>
<s:form beanclass="etanah.view.stripes.pelupusan.CarianPermohonanBerkelompokActionBean" name ="utilitiKelompok" id ="utilitiKelompok">
    <s:messages/>
    <s:errors/>
    <div class ="subtitle">
        <div class="content">
            <fieldset class ="aras1">
                <legend>Carian Permohonan</legend>
                <p><label><font color="red">*</font>ID Kelompok :</label>
                        <s:text id="idPermohonan" name="idPermohonan"></s:text>
                    </p>
                    <p align="center"><s:submit name="findKelompok" value="Cari" class="btn"/>&nbsp;<s:button name = "reset" class="btn" value ="Isi Semula" onclick= "clearText('utilitiKelompok')" /></p>

            </fieldset>
        </div>

        <div class="content">
            <fieldset class="aras1">
                <legend>
                    Senarai Kelompok
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiPermohonan}"
                                   id="line" style="width:95%"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok">                    
                        <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                            ${line_rowNum}
                        </display:column>
                        <display:column title="ID Kelompok">
                            <c:if test="${line.idPermohonan ne null}">
                                ${line.idPermohonan}
                            </c:if>
                        </display:column>
                        <display:column title="Bandar/Pekan/Mukim">                        
                            <c:forEach items="${line.senaraiHakmilik}" var="line2">
                                ${line2.bandarPekanMukimBaru.nama}
                            </c:forEach>
                        </display:column>
                        <display:column title="Tujuan">                        
                            ${line.sebab}                    
                        </display:column>
                        <display:column title="Tindakan">
                            <a onclick="editKelompok1('${line.idPermohonan}',document.forms.utilitiKelompok)" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                            <a onclick="deleteKelompok('${line.idPermohonan}',document.forms.utilitiKelompok)" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> 
                            </display:column>
                        </display:table>
                        <s:button name="tambah" value="Tambah Bertindih/Beramai" style="font-size: 10px" class="longbtn" onclick="openBertindih()" />
                        <s:button name="tambah" value="Tambah Berkelompok" class="longbtn" onclick="openKelompok()" />
                    <!--<s:button name="tambah" value="Cetak Semua" class="btn"/> -->
                </div>
            </fieldset>
        </div>

        <c:if test="${actionBean.flag}">   
            <div class="content" align="center">
                <fieldset class="aras1">
                    <legend>
                        Senarai Permohonan Terlibat
                    </legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>ID Kelompok :</td>
                                <td>${actionBean.permohonan.idPermohonan}</td>
                                <s:hidden name="idKelompok" id="idKelompok" value="${actionBean.permohonan.idPermohonan}"/>
                            </tr>
                            <tr>
                                <td>Tujuan Permohonan :</td>
                                <td>${actionBean.permohonan.sebab}</td>
                            </tr>
                            <tr>
                                <td>Status :</td>
                                <td>
                                    <c:if test="${actionBean.permohonan.status eq 'AK'}">
                                        Aktif
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.status eq 'TA'}">
                                        Tidak Ambil
                                    </c:if>    
                                </td>
                            </tr>
                        </table>
                        <br/>
                        <s:hidden name="${actionBean.sizePermohonan}" id="sizePermohonan" value="${actionBean.sizePermohonan}"/>
                        <display:table class="tablecloth" name="${actionBean.listPermohonanBerkelompokUntukIdPermohoan}"
                                       id="line" pagesize="10" style="width:95%"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/permohonan_berkelompok">                    
                            <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.idMohonKelompok}"/></display:column>
                            <display:column title="Bil" class="bil${line_rowNum}" style="${bcolor}">      
                                ${line_rowNum}
                            </display:column>
                            <display:column title="ID Permohonan">
                                <c:if test="${line.permohonan.idPermohonan ne null}">
                                    ${line.permohonan.idPermohonan}
                                </c:if>
                            </display:column>
                            <display:column title="Bandar/Pekan/Mukim">                        
                                <c:forEach items="${line.permohonan.senaraiHakmilik}" var="line2">
                                    ${line2.bandarPekanMukimBaru.nama}
                                </c:forEach>
                            </display:column>
                            <display:column title="Tujuan">                        
                                ${line.permohonan.sebab}                    
                            </display:column>
                        </display:table>
                        <s:button name="tambah" value="Tambah Permohonan" class="longbtn" onclick="tambahPermohonan()" />
                        <c:if test="${actionBean.permohonan.catatan eq 'K' && actionBean.jana eq true}">
                            <s:button name="jana" value="Jana Permohonan" class="longbtn" onclick="janaPermohonan()" />
                        </c:if>
                        <c:if test="${fn:length(actionBean.listPermohonanBerkelompokUntukIdPermohoan) > 0}" >
                            <s:button name="tambah" value="Buang Dari Kelompok" class="longbtn" onclick="selectCheckBox()"/>
                        </c:if>

                    </div>
                </fieldset>
            </div>
        </c:if>     
    </div> 
</s:form>
