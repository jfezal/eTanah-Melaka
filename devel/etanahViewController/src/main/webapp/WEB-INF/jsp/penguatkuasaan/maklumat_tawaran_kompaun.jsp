<%-- 
    Document   : maklumat_tawaran_kompaun
    Created on : Feb 23, 2010, 4:48:13 PM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function tambahBaru(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/tawaran_kompaun?popup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }
    function removeSingle(idKompaun)
    {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/tawaran_kompaun?deleteSingle&idKompaun='
                +idKompaun;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
        self.opener.refreshPage2();
    }
    function refreshPage2(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/tawaran_kompaun?refreshpage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    function popup(h){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/tawaran_kompaun?popupedit&idKompaun='+h;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");


    }


</script>
<s:form beanclass="etanah.view.penguatkuasaan.TawaranKompaunActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tawaran Kompaun
            </legend>
            <div class="content" align="center">
                <c:if test="${edit}">
                    <c:if test="${!maxSize}">
                        <div class="content" align="left">
                            <p>Klik butang tambah untuk masukkan maklumat tawaran kompaun</p>
                        </div></c:if>
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No.Siri" property="noRujukan" ></display:column>
                        <display:column title="Kompaun (RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}, ${line.noPengenalan}</display:column>
                        <display:column title="Kemaskini">
                            <div align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${line.idKompaun}')"/>
                            </div>
                        </display:column>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiKompaun[line_rowNum-1].idKompaun}');" />
                            </div>
                        </display:column>

                    </display:table>
                    <c:if test="${!maxSize}">
                        <br>
                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>
                    </c:if>
                </c:if>
                <c:if test="${view}">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="No.Siri" property="noRujukan" ></display:column>
                        <display:column title="Kompaun (RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}, No.KP : ${line.noPengenalan}</display:column>
                    </display:table>
                </c:if>
                <%--          <c:if test="${status}">
                              <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                                  <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                  <display:column title="No.Siri" property="noRujukan" ></display:column>
                                  <display:column title="Kompaun (RM)" property="amaun"></display:column>
                                  <display:column title="Tempoh (Hari)" property="tempohHari"></display:column>
                                  <display:column title="Diserahkan Kepada">${line.isuKepada}, ${line.noPengenalan}</display:column>
                                  <display:column title="Status Bayaran">
                                      <c:if test="${line.resit.idDokumenKewangan eq null}"><font color="red">Belum Dibayar</font></c:if>
                                      <c:if test="${line.resit.idDokumenKewangan ne null}">Sudah Dibayar</c:if>
                                  </display:column>
                                  <display:column title="Tarikh Bayar">
                                      <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${actionBean.tarikhBayar}"/></display:column>
                              </display:table>
                          </c:if>--%>
                <c:if test="${status}">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}, ${line.noPengenalan}</display:column>
                        <display:column title="Kompaun (RM)" property="amaun"></display:column>
                        <display:column title="Status Bayaran">
                            <c:if test="${line.resit.idDokumenKewangan eq null}"><font color="red">Belum Dibayar</font></c:if>
                            <c:if test="${line.resit.idDokumenKewangan ne null}">Sudah Dibayar</c:if>
                        </display:column>
                        <display:column title="Tarikh Bayar">
                            <c:forEach items="${actionBean.senaraiMohonTuntutBayar}" var="senarai">
                                <c:if test="${senarai.permohonanTuntutanKos.idKos eq line.permohonanTuntutanKos.idKos}">
                                    <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${senarai.tarikhBayar}"/>
                                </c:if>
                            </c:forEach>
                        </display:column>
                        <display:column title="No.Resit" property="resit.idDokumenKewangan"></display:column>
                    </display:table>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>
