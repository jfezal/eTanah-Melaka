<%--
    Document   : maklumat_hakmilik_permohonan
    Created on : 08 Oktober 2009, 3:41:04 PM
    Author     : khairil
--%>
<div id="maklumatHakmilik">
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page contentType="text/html;charset=windows-1252"%>
    <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
    <%-- <script type="text/javascript"
             src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>--%>



    <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery.bestupper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
    <script type="text/javascript">
        function popupHakmilik(){
            var url ="${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?serahHakmilikPopup";
            window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,width=900,height=600");
        }
        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function zeroPad(num,count)
        {
            var numZeropad = num + '';
            while(numZeropad.length < count) {
                numZeropad = "0" + numZeropad;
            }
            return numZeropad;
        }

        function formatNoLot(num,i){
            //alert('test');
            var noLot = zeroPad(num,'7');
            $('#noLot'+i).val(noLot);
        }

        $(document).ready( function(){
            //filterKodSeksyen();
            //filterKodGunaTanah();
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            
            if(idHakmilik != 'null'){
                //alert('masuk idHakmilik != null');
                p(idHakmilik);

            }
            
        <%--$('#simpan').click(function(){
            $('#maklumatHakmilik table.tablecloth thead a').each( function() {
                var url = $(this).attr('href');
                $(this).attr("href", "javascript:doSortPaging('"+url+"');");
            });
            $('#maklumatHakmilik .pagelinks a').each( function() {
                var url = $(this).attr('href');
                $(this).attr("href", "javascript:doSortPaging('"+url+"');");
            });
        });--%>
           
            });
        
            function selectAll(a){
                var size = '${fn:length(actionBean.hakmilikPermohonanList)}';
                //alert(size);
                for (i = 0; i < size; i++){
                    var c = document.getElementById("checkbox" + i);
                    //alert(c);
                    if (c == null) break;
                    c.checked = a.checked;
                }
            }

            function kiraCukaiKelompok(id,i){
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
                //var kodTanah = $("#kodTanah").val();
                //var kodBpm = $("#kodBpm").val();
                var kodUOM = $("#kodUOM"+i).val();
                var luas = $("#luas"+i).val();
                //alert(id);
                //alert(kodUOM);
                //alert(luas);
                //var kodRizab = $("#kodRizab").val();
                //alert(kodRizab);
                //var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?kiraCukaiKelompok&idHakmilik='+id+'&kodUOM='+kodUOM+'&luas='+luas,
                function(data){
                    if(data != ''){

                        $('#cukai'+i).val(convert(data,'cukai'+i));
                        $.unblockUI();
                    }
                }, 'html');
            }

            function removeMultipleMohonHakmilik(){
                if (confirm('Adakah anda pasti untuk hapus hakmilik ini')){

                    var param = '';
                    $('.remove2').each(function(index){
                        var a = $('#checkbox'+index).is(":checked");
                        if(a) {
                            param = param + '&idMohonHakmilik=' + $('#checkbox'+index).val();
                        }
                    });

                    if(param == ''){
                        alert('Sila Pilih Hakmilik terlebih dahulu.');
                        return;
                    }

                    var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteMultipleMohonHakmilik'+param;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');
                }
            }

            function genHakmilikStrata(){
                alert("masuk");
                var url = '${pageContext.request.contextPath}/common/maklumat_hakmilik_permohonan?generateIDHakmilikStrata';
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
                
            }


            function p(v){
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });

                $.get("${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?idHakmilik="+v,
                function(data){
                    //alert(data);
                    $("#perincianHakmilik").show();
                    $("#perincianHakmilik").html(data);
                    $.unblockUI();
                });

            }
            function filterKodSeksyen(){
                //alert('filter');
                var kodBpm = $("#kodBpm").val();
                var kodDaerah = $("#kodDaerah").val();
                //alert(kodBpm);
                //alert(kodDaerah);
                //var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiSeksyenByBPM&kodBpm='+kodBpm+'&kodDaerah='+kodDaerah,
                function(data){
                    if(data != ''){
                        $('#partialKodSeksyen').html(data);
                    }
                }, 'html');
            }

            function filterKodGunaTanah(){
                //alert('filter');
                var katTanah = $("#katTanah").val();
                //alert(katTanah);
                // var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiKodGunaTanahByKatTanah&kodKatTanah='+katTanah,
                function(data){
                    if(data != ''){
                        $('#partialKodGunaTanah').html(data);
                    }
                }, 'html');
            }

            function removeHakmilik(val){
                //var id = $('#hiddenIdHakmilik').val();
                var answer = confirm("adakah anda pasti untuk Hapus?");
                if (answer){
                    var url = '${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?deleteHakmilik&idMh='+val;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    });
                }
            }
            
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
            }

        


    </script>
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean">

        <div class="subtitle displaytag">
            <div class="subtitle">
            <s:messages/>
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik Baru  
                </legend>
                <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                    <p align="center"><label></label>
                        <s:hidden name="sizeHakmilikPermohonan" value="${fn:length(actionBean.hakmilikPermohonanList)}" id="sizeHakmilikPermohonan"/>
                        <display:table class="tablecloth" style="width:90%;" requestURI="/common/maklumat_hakmilik_permohonan" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="line">
                            <%--<display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);' />">
                           <stripes:checkbox name="senaraiDokumenSerahan[${row_rowNum - 1}].kodDokumen"
                                             value="${row.kodDokumen.kod}" id="kandunganFolder${row_rowNum - 1}" /></display:column>--%>
                            <display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);'>">
                                <s:checkbox name="checkbox" id="checkbox${line_rowNum-1}" value="${line.id}" class="remove2"/>
                            </display:column>
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <display:column title="ID Hakmilik"><a href="#" onclick="p('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
                                <s:hidden name="hiddenIdHakmilik" id="hiddenIdHakmilik${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                            </display:column>                                           
                            <c:if test="${actionBean.p.kodUrusan.kod eq 'HT' || actionBean.p.kodUrusan.kod eq 'HTSC' || actionBean.p.kodUrusan.kod eq 'HTSPB' || actionBean.p.kodUrusan.kod eq 'HTSPS' || actionBean.p.kodUrusan.kod eq 'HTSPV'}">
                                <display:column title="No Bangunan">
                                    ${line.hakmilik.noBangunan}
                                </display:column>
                                <display:column title="No Tingkat">
                                    ${line.hakmilik.noTingkat}
                                </display:column>
                                <display:column title="No Petak">
                                    ${line.hakmilik.noPetak}
                                </display:column>
                                <display:column title="Luas / Unit Petak">
                                    <s:text name="hakmilikPermohonanList[${line_rowNum-1}].hakmilik.luas" id="luas${line_rowNum-1}" value="${line.hakmilik.luas}" formatPattern="###0.0000" size="10" onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"/>
                                    <s:select  style="text-transform:uppercase" id="kodUOM${line_rowNum-1}" name="strKodUOM[${line_rowNum-1}]" onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');">
                                        <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                                    </s:select>
                                </display:column>
                            </c:if>

                            <c:if test="${actionBean.p.kodUrusan.kod ne 'HT' && actionBean.p.kodUrusan.kod ne 'HTSC' && actionBean.p.kodUrusan.kod ne 'HTSPB' && actionBean.p.kodUrusan.kod ne 'HTSPV' && actionBean.p.kodUrusan.kod ne 'HTSPS'}">
                                <display:column title="Luas">
                                    ${line.hakmilik.kodUnitLuas.nama}
                           
                                    </display:column>
                                
                                <display:column title="Unit">
                                    <s:text name="hakmilikPermohonanList[${line_rowNum-1}].hakmilik.luas" id="luas${line_rowNum-1}" value="${line.hakmilik.luas}" formatPattern="###0.00" size="10" onchange="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}');"/>
                                    
                                    <%--<c:if test="${actionBean.p.kodUrusan.kod ne 'HSSTA'||actionBean.p.kodUrusan.kod ne 'HKSTA'}">--%>
                                    <%--<s:text name="hakmilikPermohonanList[${line_rowNum-1}].hakmilik.luas" id="luas${line_rowNum-1}" value="${line.hakmilik.luas}" formatPattern="###0.0000" size="10"/>
                                    <s:select  style="text-transform:uppercase" id="kodUOM${line_rowNum-1}" name="strKodUOM[${line_rowNum-1}]">
                                        <s:options-collection collection="${list.senaraiKodUOMByJarak}" label="nama" value="kod"/>
                                    </s:select>
                                    </c:if>--%>
                                </display:column>
                                <display:column title="No Lot / No PT">
                                    <s:select  style="text-transform:uppercase" id="kodLot${line_rowNum-1}" name="kodLot[${line_rowNum-1}]">
                                        <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod"/>
                                    </s:select>
                                    <s:text name="hakmilikPermohonanList[${line_rowNum-1}].hakmilik.noLot" size="8" maxlength="7" value="${line.hakmilik.noLot}" id="noLot${line_rowNum-1}" onblur="formatNoLot(this.value,'${line_rowNum-1}');"/>
                                </display:column>

                                <c:if test="${fn:contains(!actionBean.p.idPermohonan, 'STR')}"> <%--added by murali 26-06-2012 @PAT Melaka--%>
                                    <display:column title="Cukai">
                                        RM <s:text name="hakmilikPermohonanList[${line_rowNum-1}].hakmilik.cukai" formatPattern="###0" value="${line.hakmilik.cukai}" id="cukai${line_rowNum-1}"/>
                                    </display:column>
                                </c:if>
                                <%--  <display:column property="hakmilik.daerah.nama" title="Daerah" />
                                  <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />--%>
                                <display:column title="Draf DHDe <br/>"
                                                style="width:20%;">
                                    <c:if test="${line.dokumen1.namaFizikal != null}">
                                        <a href="#" id="p" onclick="doViewReport('${line.dokumen1.idDokumen}');return false;">Papar</a>
                                    </c:if>
                                </display:column>


                                <display:column title="Pelan B1 DHDe / B2 DHDe <br/>"
                                                style="width:20%">
                                    <c:if test="${line.dokumen5.namaFizikal != null}">
                                        <a href="#" id="p" onclick="doViewReport('${line.dokumen5.idDokumen}');return false;">Papar</a>

                                        <c:set var="path"/>
                                        <c:forTokens delims="/" items="${line.dokumen5.namaFizikal}" var="items" begin="0" end="3">
                                            <c:set var="path" value="${path}/${items}"/>
                                        </c:forTokens>
                                        <input type="hidden" id="pelan_path${line_rowNum}" value="${path}"/>
                                    </c:if>
                                </display:column>
                                <display:column title="Pelan B1 DHKe / B2 DHKe <br/>"
                                                style="width:20%">
                                    <c:if test="${line.dokumen6.namaFizikal != null}">
                                        <a href="#" id="p" onclick="doViewReport('${line.dokumen6.idDokumen}');return false;">Papar</a>
                                        <c:set var="path"/>
                                        <c:forTokens delims="/" items="${line.dokumen6.namaFizikal}" var="items" begin="0" end="3">
                                            <c:set var="path" value="${path}/${items}"/>
                                        </c:forTokens>
                                        <input type="hidden" id="pelan_path_2_${line_rowNum}" value="${path}"/>
                                    </c:if>
                                </display:column>
                                <c:if test="${actionBean.p.kodUrusan.kod eq 'HSSTA'|| actionBean.p.kodUrusan.kod eq 'HKSTA'}"></c:if>
                                <display:column title="Cukai">
                                    <s:text name="hakmilikPermohonanList[${line_rowNum-1}].hakmilik.cukai" id="cukai${line_rowNum-1}" value="${line.hakmilik.cukai}" formatPattern="###0.00" size="10"/>
                                </display:column>
                            </c:if>

                        </display:table>
                        &nbsp;

                    </c:if>
                <p>
                    <label>&nbsp;</label>
                    <%-- <s:button name="popup" id="popup" value="Tambah" class="btn" onclick="popupHakmilik();"/>--%>
                    <c:if test="${fn:length(actionBean.hakmilikPermohonanList) > 0}">
                        <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonHakmilik();"/>
                        
                        <s:button class="longbtn" value="Simpan Berkelompok" name="simpanBerkelompok" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        &nbsp;
                    </c:if>
                    <%-- <c:if test="${actionBean.p.kodUrusan.kod eq 'HT'}">
                         <s:button class="longbtn" value="Jana Hakmilik Strata" name="" onclick="genHakmilikStrata();"/>
                     </c:if>--%>
                    <%-- <s:button class="btn" value="Hapus" name="" onclick="removeMultipleMohonHakmilik();"/>&nbsp;--%>
                </p>
                <br>

            </fieldset>
        </div>
        </div>
    </s:form>

    <div id="perincianHakmilik">
    </div>
</div>



