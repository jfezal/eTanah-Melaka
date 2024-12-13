<%-- 
    Document   : rekod_keputusan_rayuan_mlk
    Created on : Jun 16, 2011, 4:46:27 PM
    Author     : Akmal
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">
    
    $(document).ready( function(){
        if(${actionBean.permohonan.keputusan.kod eq 'L'}){
            $('#lulus').show() ;
        }
        else {
            $('#lulus').hide() ;
        }
        if($('#catatan').val() == 'N'){
            $('#nominal').show() ;
            $('#bukanNominal').hide() ;
        }
        else {
            $('#nominal').hide() ;
            $('#bukanNominal').show() ;
            
        }
    });
    
    function statusKeputusan(keputusan)
    {
        var url = '${pageContext.request.contextPath}/pelupusan/keputusan_rayuan?simpanKeputusan&keputusan=' + keputusan;
        $.get(url,
        function(data){
            $('#page_div').html(data); 
            self.close();
            opener.refreshRayuan() ;
                
           
        },'html');
    }
    
    function save(event, f){
        if(doValidation()){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
            },'html');
        }

    }
    
    
    
    function forNominal(){
        //        alert($('#ulasan').val());
        if($('#catatan').val() == 'N'){
            $('#nominal').show() ;
            $('#bukanNominal').hide() ;
            $('#nilai').val(0) ;
        }
        else {
            $('#nominal').hide() ;
            $('#bukanNominal').show() ;
        }
    }
    
    function forKpsnLulus(){
        $('#lulus').show() ;
    }
    
    function forKpsnTolak(){
        $('#lulus').hide() ;
    }
    function forKpsnTangguh(){
        $('#lulus').hide() ;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.KeputusanRayuanActionBean">
    <s:errors/>
    <s:messages/>
    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Keputusan</legend>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL' || actionBean.permohonan.kodUrusan.kod eq 'RLPTG'}">
                    <table>
                        <tr>
                            <td><label>Keputusan:</label></td>
                            <c:if test="${!empty actionBean.fasaPermohonan.keputusan.kod}">
                                <td>${actionBean.fasaPermohonan.keputusan.nama} </td>
                            </c:if>
                            <c:if test="${empty actionBean.fasaPermohonan.keputusan.kod}">
                                <td>Tiada keputusan yang dibuat setakat ini</td>
                            </c:if>                            
                        </tr>
                        <tr>
                            <td><label>Tempoh Dipohon:</label></td>
                            <td>${actionBean.permohonan.tempohBulan} Bulan </td>
                        </tr>
                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'L'}">
                            <tr>
                                <td><label>Tempoh Diluluskan:</label></td>
                                <td><s:text name="lanjutBayaran.tempoh" size="20" id="tempoh" maxlength="3" value="${actionBean.lanjutBayaran.tempoh}"/>
                                    <s:select name="tempohUom.kod" id="tempohUOM" value="${actionBean.lanjutBayaran.tempohUom.kod}">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:option value="B">Bulan</s:option>
                                    </s:select></td>
                            </tr>
                        </c:if>
                    </table>
                    <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'L'}">
                        <div align="center">
                            <s:button name="simpanSyarat" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYK' || actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                    <table>

                        <tr>
                            <td><label>Premium Asal: RM</label></td>
                            <td> <s:format value="${actionBean.permohonanTuntutanKos.amaunTuntutan}" formatPattern="#,##0.00"/></td>
                        </tr>
                        <tr>
                            <td><label>Premium Dipohon: RM</label></td>
                            <td> <s:format value="${actionBean.permohonan.nilaiDipohon}" formatPattern="#,##0.00"/></td>
                        </tr>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYK'}">
                            <tr>
                                <td><label>Keputusan:</label></td>
                                <td> <s:radio name="keputusan" id="kpsn" value="L" onclick="forKpsnLulus();"/>Lulus&nbsp;
                                    <s:radio name="keputusan" id="kpsn" value="T" onclick="forKpsnTolak();"/>Tolak&nbsp;</td>
                            </tr>          
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                            <tr>
                                <td><label>Keputusan:</label></td>
                                <td> <s:radio name="keputusan" id="kpsn" value="L" onclick="forKpsnLulus();"/>Lulus&nbsp;
                                    <s:radio name="keputusan" id="kpsn" value="T" onclick="forKpsnTolak();"/>Tolak&nbsp;
                                    <s:radio name="keputusan" id="kpsn" value="TG" onclick="forKpsnTangguh();"/>Tangguh&nbsp;</td>
                            </tr>          
                        </c:if>

                        <tr>
                            <td><label>No Risalat :</label></td>
                            <td><s:text name="noSidang"/></td>
                        </tr>
                        <tr>
                            <td><label>Tarikh Sidang :</label></td>
                            <td><s:text name="tarikhSidang" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                        </tr>

                        <tr>
                            <td><label>Tarikh Sah Keputusan :</label></td>
                            <td><s:text name="tarikhKeputusan" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                        </tr>
                        <tr>
                            <td><label>Catatan :</label></td>
                            <td><s:textarea name="permohonan.ulasan" cols="50" rows="5"/></td>
                        </tr>
                        <tr>

                        </tr>
                    </table>
                    <div id="lulus">
                        <p><label>Premium Diluluskan: RM </label>

                            <%-- <s:select name="permohonan.catatan" id="catatan" onchange="forNominal()">
                                <s:option value="">Sila Pilih</s:option>
                                <s:option value="N">Nominal</s:option>
                                <s:option value="0.25">25%</s:option>
                                <s:option value="0.5">50%</s:option>
                            </s:select> --%>

                            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'RYKN'}">
                                <s:select name="permohonan.catatan" id="catatan" onchange="forNominal()">
                                    <s:option value="">Sila Pilih</s:option>
                                    <s:option value="N">Nominal</s:option>
                                    <s:option value="0.25">25%</s:option>
                                    <s:option value="0.5">50%</s:option>
                                </s:select>
                            </c:if>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                                <s:select name="permohonan.catatan" id="catatan" onchange="forNominal()">
                                    <s:option value="N">Nominal</s:option>
                                    <%-- <s:option value="0.25">25%</s:option>
                                    <s:option value="0.5">50%</s:option> --%>
                                </s:select> 
                            </c:if> 

                        </p>

                        <div id="bukanNominal">
                            <p>
                                <label>&nbsp;</label>
                                <s:format value="${actionBean.permohonan.nilaiKeputusan}" formatPattern="#,##0.00"/>
                            </p>
                        </div>
                        <div id="nominal">
                            <p>
                                <label>&nbsp;</label>
                                <s:text name="permohonan.nilaiKeputusan" id="nilai"/>
                            </p>
                        </div>
                    </div>


                    <div align="center">
                        <s:button name="simpanPremiumLulus" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                    </div>
                </c:if>
            </fieldset>
        </div>
    </c:if>

    <c:if test="${!edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Keputusan Rayuan</legend>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL' || actionBean.permohonan.kodUrusan.kod eq 'RLPTG'}">
                    <table>
                        <tr>
                            <td><label>Keputusan :</label></td>
                            <td>${actionBean.fasaPermohonan.keputusan.nama}</td>
                        </tr>
                        <tr>
                            <td><label>Tarikh Keputusan :</label></td>
                            <td><s:format value="${actionBean.fasaPermohonan.tarikhKeputusan}" formatPattern="dd/MM/yyyy"/> </td>
                        </tr>
                        <tr>
                            <td><label>Tempoh Dipohon:</label></td>
                            <td>${actionBean.permohonan.tempohBulan} Bulan </td>
                        </tr>
                        <c:if test="${actionBean.fasaPermohonan.keputusan.kod eq 'L'}">
                            <tr>
                                <td><label>Tempoh Diluluskan :</label></td>
                                <td>${actionBean.lanjutBayaran.tempoh} ${actionBean.lanjutBayaran.tempohUom.nama} </td>
                            </tr>
                        </c:if>

                    </table>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYK' || actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                    <table>
                        <tr>
                            <td><label>Premium Asal: RM</label></td>
                            <td> 
                                <c:if test="${actionBean.kodNegeri eq '04'}">
                                    <s:format value="${actionBean.permohonanTuntutanKos.amaunTuntutan}" formatPattern="#,##0.00"/>
                                </c:if>
                                <c:if test="${actionBean.kodNegeri eq '05'}">
                                    <c:if test="${actionBean.permohonan.keputusan.kod eq 'L'}">
                                        <s:format value="${actionBean.permohonanTuntutanKos.amaunSebenar}" formatPattern="#,##0.00"/>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.keputusan.kod ne 'L'}">
                                        <s:format value="${actionBean.permohonanTuntutanKos.amaunTuntutan}" formatPattern="#,##0.00"/>
                                    </c:if>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Premium Dipohon: RM</label></td>
                            <td> <s:format value="${actionBean.permohonan.nilaiDipohon}" formatPattern="#,##0.00"/></td>
                        </tr>
                        <tr>
                            <td><label>Keputusan</label></td>
                            <td> ${actionBean.permohonan.keputusan.nama}</td>
                        </tr>
                        <tr>
                            <td><label>No. Risalat</label></td>
                            <td> ${actionBean.noSidang}</td>
                        </tr>
                        <tr>
                            <td><label>Tarikh Sidang</label></td>
                            <td> <s:format value="${actionBean.tarikhSidang}" formatPattern="dd/MM/yyyy"/></td>
                        </tr>

                        <tr>
                            <td><label>Tarikh Keputusan</label></td>
                            <td> <s:format value="${actionBean.tarikhKeputusan}" formatPattern="dd/MM/yyyy"/></td>
                        </tr>
                        <tr>
                            <td><label>Catatan</label></td>
                            <td> ${actionBean.permohonan.ulasan}</td>
                        </tr>
                        <tr>
                            <c:if test="${actionBean.permohonan.keputusan.kod eq 'L'}">
                                <td><label>Premium Diluluskan: RM </label></td>
                                <td><s:format value="${actionBean.permohonan.nilaiKeputusan}" formatPattern="#,##0.00"/></td>
                            </c:if>
                        </tr>
                    </table>
                </c:if>

            </fieldset>
        </div>
    </c:if>

</s:form>