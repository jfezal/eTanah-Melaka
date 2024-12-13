<%--
    Document   : maklumat_hakmilik_permohonan
    Created on : 08 Oktober 2009, 3:41:04 PM
    Author     : khairil
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript">
    $(document).ready( function() {
    <%--dialogInit('Carian Hakmilik');--%>
                var len = $(".daerah").length;

                for (var i=0; i<=len; i++){
                    $('.hakmilik'+i).click( function() {
                        window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=600");
                    });
                }
        
            });
            function removeHakmilik(val){
                // var id = $('#hiddenIdHakmilik').val();
                var answer = confirm("adakah anda pasti untuk Hapus?");
                if (answer){
                    var url = '${pageContext.request.contextPath}/common/maklumat_hakmilik_permohonan?deleteHakmilik&idHakmilik='+val;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    });
                }
            }
            function popupTambahHakmilik(){
                var url ="${pageContext.request.contextPath}/common/maklumat_hakmilik_permohonan?showCarianHakmilik";
                window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,width=900,height=600");
            }
</script>
<s:errors />
<s:messages />
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean">
    <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Permohonan
            </legend>
            <%--<div class="content" align="center">
                Lot Menanggung
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanMenanggung}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No Lot" />
                      <display:column title="Kod Tanggungkuasa Ismen">
                          <s:select style="text-transform:uppercase" name="hubunganHakmilik" value="${hubunganHakmilik}">
                              <s:option value="">Sila Pilih</s:option>
                              <s:options-collection collection="${list.senaraiKodPerhubunganHakmilik}" label="nama" value="kod" />
                          </s:select>
                      </display:column>
                    <display:column property="hakmilik.luas" title="Luas" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                </display:table>
            </div>--%>

            <%--<div class="content" align="center">
                Lot Menguasai
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanMenguasai}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line2">
                    <display:column title="Bil">${line2_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line2_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No Lot" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line2.hakmilik.luas}"/>&nbsp;${line2.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Padam' border='0' src='${pageContext.request.contextPath}/pub/images/not_ok.gif' class='rem'
                                 id='rem_${line2_rowNum}' onclick="removeHakmilik('${line2.hakmilik.idHakmilik}');" style="cursor:hand">
                        </div>
                    </display:column>
                </display:table>
                <s:button name="popuptambahhakmilik" id="popuptambahhakmilik" value="Tambah" class="btn" onclick="popupTambahHakmilik();"/>&nbsp;
            </div>--%>
            <div id="" class="content" align="center">                
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line2">
                    <display:column title="Bil">${line2_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line2_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No Lot" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line2.hakmilik.luas}"/>&nbsp;${line2.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Jenis Lot">
                        <s:select name="jenisLot[${line2_rowNum-1}]" style="width:400px">
                            <s:option value="">Sila Pilih</s:option>
                            <c:forEach items="${list.senaraiKodPerhubunganHakmilik}" var="item">
                                <c:if test="${item.kod ne 'GT' && item.kod ne 'GL' && (fn:length(fn:trim(item.kod)) > 1) }">
                                    <s:option value="${item.kod}">${item.nama}</s:option>
                                </c:if>
                            </c:forEach>
                            <%--<s:options-collection collection="${list.senaraiKodPerhubunganHakmilik}" label="nama" value="kod"/>--%>
                        </s:select>
                    </display:column>
                </display:table>
                <br/>
                <p>
                    <label></label>
                    <s:button value="Simpan" name="simpanKuasaTanggung" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                </p>
            </div>
        </fieldset>
    </div>
    <div id="list_pb"/>
</s:form>

