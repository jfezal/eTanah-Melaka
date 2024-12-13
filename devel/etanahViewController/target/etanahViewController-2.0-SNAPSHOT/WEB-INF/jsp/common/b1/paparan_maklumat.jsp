<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function papar(s) {
        window.open("${pageContext.request.contextPath}/common/b1?papar&idPermohonan=" + s, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1400px,height=800px,scrollbars=yes");
    }
    function muatNaik(s) {
        window.open("${pageContext.request.contextPath}/common/b1/kemasukan_pelan_b1?muatNaik&idPermohonan=" + s, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=800px,height=400px,scrollbars=yes");
    }

</script>
<s:form beanclass="etanah.view.stripes.common.b1.PaparanMaklumatB1ActionBean" id="form" name="form" >
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Maklumat Hakmilik/Pelan</legend>
          <!--  
            <p>
                <label for="ID Permohonan">No Fail Ukur :</label>
                &nbsp;
            </p> -->
            <table class="tablecloth" id="sa">
                <tr>
                    <th>Maklumat Hakmilik</th>
               
                <th>Jumlah Hakmilik QT Yang Terlibat</th>
                <th>Jumlah Pelan Telah DiMuat Naik</th>
                <th>Baki Pelan Yang Perlu DiMuat Naik</th>
                <th>Tindakan</th>

                </tr>
                <tr>
                    <td> 
                        <table class="tablecloth" id="s">
                            <tr>
                            <th>Jenis Hakmilik</th>
                              <th>Daerah</th>
               
                <th>Bandar/Pekan/Mukim</th>
                <th>Seksyen</th>
                            </tr>
                            <tr><td>${actionBean.jenisHakmilik}</td>
                                <td>${actionBean.daerah}</td>
                                <td>${actionBean.bpm}</td>
                                <td>${actionBean.seksyen}</td>
                            </tr>
                            
                </table>
                    </td>
                    <td style="text-align: center;font-size: x-large">${actionBean.hakmilikQT}</td>
                    <td style="text-align: center;font-size: x-large">${actionBean.pelanMuatNaik}</td>
                    <td style="text-align: center;font-size: x-large">${actionBean.balPelan}</td>
                    <td>     <s:button name="terperinci" value="Muat Naik" class="btn"  onclick="muatNaik('${actionBean.idPermohonan}')"/>                     
                        <br><br> <s:button name="terperinci" value="Kemaskini" class="btn"  onclick="papar('${actionBean.idPermohonan}')"/>                     
                    </td>

                </tr>

            </table>


        </fieldset >
    </div>

</s:form>

