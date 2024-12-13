<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>


<style type="text/css">
    input.error { background-color: yellow; }
</style>
<script type="text/javascript">
    $(document).ready(function() {
        $('.popup').each(function() {
            $(this).html('<u>' + $(this).text() + '</u>');
            $(this).mouseover(function() {
                $(this).addClass("cursor_pointer");
            });
        });
    <%--var url = '${pageContext.request.contextPath}/daftar/check_pihak?doCheckPihak&idHakmilik=${actionBean.idHakmilik}';
    $.get(url,
    function(data){
        if(data == '1'){
            alert('Hakmilik ada kaveat. Sila semak surat kebenaran.')
        }
    });--%>

        var len = $(".popup").length;

        for (var i = 0; i <= len; i++) {
            $('.permohonan' + i).click(function() {
                window.open("${pageContext.request.contextPath}/common/maklumat_permohonan?viewPermohonan&idPermohonan=" + $(this).text(), "eTanah",
                        "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=930,height=600");
            });
        }

        enableText($('#cara_terima').val());
    });

    function enableText(v) {
        if (v == 'Lain-lain') {
            $('#sbb_lain').show();
        } else {
            $('#sbb_lain').hide();
        }
    }

    function validate() {
        if ($('#cara_terima').val() == 'Lain-lain') {
            if ($('#sbb_lain_text').val() == '') {
                alert('Sila Masukan Sebab lain-lain');
                $('#sbb_lain').focus();
                return false;
            }
        }
        return true;
    }

    function deleteCP(idPermohonan1, idPermohonanCP) {
        alert(idPermohonan1);
        alert(idPermohonanCP);
        doBlockUI();
        var url = "${pageContext.request.contextPath}/common/maklumat_permohonan?deleteCertificatePerson&idPermohonan1=" + idPermohonan1 + '&idPermohonanCP=' + idPermohonanCP;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${!empty warning || !empty warning2 || !empty warning3 || !empty warning4
                  || !empty warning5 || !empty warning6 || !empty warning7 || !empty warning8|| !empty warning9}">
          <div class="warning">
              <p>
                  <c:if test="${!empty warning}">
                      ${warning} <br/>
                  </c:if>
                  <c:if test="${!empty warning2}">
                      ${warning2} <br/>
                  </c:if>
                  <c:if test="${!empty warning3}">
                      ${warning3} <br/>
                  </c:if>
                  <c:if test="${!empty warning4}">
                      ${warning4} <br/>
                  </c:if>
                  <c:if test="${!empty warning5}">
                      ${warning5} <br/>
                  </c:if>
                  <c:if test="${!empty warning6}">
                      ${warning6} <br/>
                  </c:if>
                  <c:if test="${!empty warning7}">
                      <font color="red">${warning7}</font> <br/>
                      </c:if>
                      <c:if test="${!empty warning8}">
                          ${warning8} <br/>
                  </c:if>
                  <c:if test="${!empty warning9}">
                      ${warning9} <br/>
                  </c:if>    
              </p>
          </div>        
    </c:if>
    <%--<c:if test="${!empty warning2}">
        <div class="warning"><p>${warning2}</p></div>
    </c:if>
         <c:if test="${!empty warning3}">
        <div class="warning"><p>${warning3}</p></div>
    </c:if>--%>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Maklumat Perserahan</legend>
            <p>
                <label for="Permohonan">Perserahan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Perserahan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
            <%--<p>
                <label for="ID Permohonan">Sebab-Sebab :</label>
                ${actionBean.permohonan.sebab}&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">Keterangan-Keterangan Lain :</label>
                ${actionBean.permohonan.catatan}&nbsp;
            </p>--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMT' || actionBean.permohonan.kodUrusan.kod eq 'TMAME'
                          || actionBean.permohonan.kodUrusan.kod eq 'TMAMF' || actionBean.permohonan.kodUrusan.kod eq 'TMAMG'
                          || actionBean.permohonan.kodUrusan.kod eq 'TMAMW' || actionBean.permohonan.kodUrusan.kod eq 'TMAMD'
                          || actionBean.permohonan.kodUrusan.kod eq 'PMG' || actionBean.permohonan.kodUrusan.kod eq 'PMP'
                          || actionBean.permohonan.kodUrusan.kod eq 'TMAMT' || actionBean.permohonan.kodUrusan.kod eq 'JMGD'
                  }">
                <p>
                    <label>Cara Terima Bahagian :</label>                    
                    <s:select name="permohonan.sebab" id="cara_terima" onchange="enableText(this.value);" style="valign:top">
                        <%--<s:select name="permohonan.sebab" onchange="enableText(this.value);" id="cara_terima">--%>
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-map map="${list.senaraiCaraTerimaSyer}"/>
                    </s:select>&nbsp;<%--<s:text name="sbb_lain" style="display:none" id="sbb_lain"/>--%>                    
                    <s:button name="saveCaraTerimaBahagian" class="btn" value="Simpan" onclick="if(validate())doSubmit(this.form,this.name,'page_div');"/>
                </p>
                <p style="display: none"  id="sbb_lain">
                    <label>&nbsp;</label>
                    <s:textarea cols="40" rows="6" name="permohonan.ulasan" id="sbb_lain_text"/>
                </p>
                <%-- <p>
                     <label>&nbsp;</label>
                     <s:button name="saveCaraTerimaBahagian" class="btn" value="Simpan" onclick="if(validate())doSubmit(this.form,this.name,'page_div');"/>
                 </p>--%>
            </c:if>
            <br>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label for="Nama">Nama Penyerah :</label>
                ${actionBean.permohonan.penyerahNama}&nbsp;
            </p>
            <p>
                <label for="Nama">Nombor Rujukan Penyerah :</label>
                <c:if test="${actionBean.permohonan.penyerahNoRujukan ne null}">
                    ${actionBean.permohonan.penyerahNoRujukan}&nbsp;
                </c:if>
                <c:if test="${actionBean.permohonan.penyerahNoRujukan eq null}">
                    -
                </c:if>
            </p>          
            <p>
                <label for="Alamat">Alamat Penyerah :</label>                
                ${actionBean.permohonan.penyerahAlamat1}&nbsp;
            </p>
            <c:if test="${actionBean.permohonan.penyerahAlamat2 ne null}">
                <p>
                    <label>&nbsp;</label>
                    ${actionBean.permohonan.penyerahAlamat2}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat3 ne null}">
                <p>
                    <label>&nbsp;</label>
                    ${actionBean.permohonan.penyerahAlamat3}&nbsp;
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.penyerahAlamat4 ne null}">
                <p>
                    <label>Bandar :</label>
                    ${actionBean.permohonan.penyerahAlamat4}&nbsp;
                </p>
            </c:if>
            <p>
                <label for="Poskod">Poskod :</label>
                <c:if test="${actionBean.permohonan.penyerahPoskod ne null}">
                    ${actionBean.permohonan.penyerahPoskod}&nbsp;
                </c:if>
                <c:if test="${actionBean.permohonan.penyerahPoskod eq null}">
                    -
                </c:if>
            </p>
            <p>
                <label for="Poskod">Negeri :</label>
                <c:if test="${actionBean.permohonan.penyerahNegeri.nama ne null}">
                    ${actionBean.permohonan.penyerahNegeri.nama}&nbsp;
                </c:if>
                <c:if test="${actionBean.permohonan.penyerahNegeri.nama eq null}">
                    -
                </c:if>
            </p>
            <br>
        </fieldset>
    </div>
    <br/>
    <%--TODO filter by kod urusan--%>

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMT'}" >
            <div class="subtitle">


                <fieldset class="aras1">
                    <legend>Maklumat Permohonan Certificate Person</legend>
                    <center>
                        <display:table class="tablecloth" name="${actionBean.wakilKuasaList}" cellpadding="0"
                                       cellspacing="0" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column property="permohonan.idPermohonan" title="ID Perserahan" />
                            <display:column property="idWakil" title="Id Certificate Person"/>
                            <display:column title="Hapus">
                                <%--<c:if test="${line.dokumen ne null}">--%>
                                <a onclick="deleteCP('${line.permohonan.idPermohonan}', '${line.idWakil}')" onmouseover="this.style.cursor = 'pointer';">
                                    <center><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/pictures.png'/></center>
                                </a>

                                <%--</c:if>--%>
                            </display:column>
                        </display:table>
                    </center>
                    <br><br>

                    <p>
                        <label for="Nama">ID Certificated Person :</label>
                        <s:text name="idCP" size="30"/>
                        <s:button name="searchMaklumatCP" value="Cari" class="btn" onclick="doSubmit(this.form,this.name,'page_div');"/>
                        <s:button name="janaCPbaru" value="Jana baru" class="btn" onclick="doSubmit(this.form,this.name,'page_div');"/>
                    </p>
                </fieldset>
            </div>
        </c:if>


    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'SMB' && actionBean.permohonan.kodUrusan.kod ne 'SMK' && actionBean.permohonan.kodUrusan.kod ne 'SMBT'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan/Perserahan Sebelum</legend>
                <br/>
                <font color="black" size="2">Untuk Pengesahan Permohonan Sebelum.</font>
                <br/>
                <br/>
                <p>
                    <label for="Nama">ID Permohonan Sebelum :</label>
                    <s:text name="idPermohonanTerdahulu" size="30"/>
                    <s:button name="searchMaklumatTerdahulu" value="Cari" class="btn" onclick="doSubmit(this.form,this.name,'page_div');"/>
                </p>
                <br/>
                <br/>
                <c:if test="${fn:length(actionBean.permohonanTerdahuluList) > 0}">
                    <br/>
                    <font size="1" color="black">Sila Klik pada ID untuk paparan terperinci.</font><br/>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.permohonanTerdahuluList}" cellpadding="0"
                                       cellspacing="0" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column property="idPermohonan" title="ID Perserahan" class="popup permohonan${line_rowNum}"/>
                            <display:column property="kodUrusan.nama" title="Urusan"/>
                            <display:column property="keputusan.nama" title="Keputusan" />
                            <display:column title="Tarikh Keputusan">
                                <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aa" value="${line.tarikhKeputusan}"/>
                            </display:column>
                            <display:column title="Keputusan Oleh" >${fn:toUpperCase(line.keputusanOleh.nama)}</display:column>
                        </display:table>
                    </div>
                </c:if>
                <%--<c:if test="${fn:length(actionBean.permohonanTerdahuluList) == 0}">
                    <p>
                        <label>&nbsp</label>
                        Tiada Maklumat.
                    </p>
                </c:if>--%>
            </fieldset>
        </div>
    </c:if>
    <br/>       
</s:form>
